
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author RDDZXA
 * 
 * Az osztály a játák szimulációt reprezentálja.
 */
public class Game {

    /**
     * Beolvas a fájlból és létrehozza a mezőket és a játékosokat.Ha van az input fájlban előre rögzített kockadobás, akkor azt is beolvassa.
     * @param fields  a táblán a mezők listája
     * @param players  a játékosok listája
     * @param diceRolls  dobások listája
     * @param file  beolvasandó file
     * @throws java.io.IOException
     * @throws SomethingWrongWithTheFileException  ha valami nem stimmel a beolvasásnál
     */
    public static void createAll(Board fields, ArrayList<Player> players, ArrayList<Integer> diceRolls, String file) throws IOException, SomethingWrongWithTheFileException{
        Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));
        int db = sc.nextInt();
        
        int i=0;
        while(i<db){
            switch(sc.next()){
                case "p":
                    fields.increase(new Property());
                    break;
                case "l":
                    fields.increase(new Luck(sc.nextInt()));
                    break;
                case "s":
                    fields.increase(new Service(sc.nextInt()));
                    break;
                default:
                    throw new SomethingWrongWithTheFileException();
            }
            
            i++;
        }
        
        db = sc.nextInt();
        i=0;
        while(i<db){
            String name = sc.next();
            switch(sc.next()){
                case "mohó":
                    players.add(new Greedy(name, fields.start()));
                    break;
                case "taktikus":
                    players.add(new Tactician(name, fields.start()));
                    break;
                case "óvatos":
                    players.add(new Cautious(name, fields.start()));
                    break;
                default:
                    throw new SomethingWrongWithTheFileException();
            }
            i++;
        }
        
        while(sc.hasNext()){
            diceRolls.add(sc.nextInt());
        }
        
        sc.close();
    }
    
    /**
     * Visszaadja a bevitt értékeket.
     * @param players  a játékosok listája
     * @param b  a tábla
     * @param dice  a dobott számok listája
     */
    public static void report(ArrayList<Player> players, Board b, ArrayList<Integer> dice){
        System.out.println("//Játékosok: ");
        for(Player p: players){
            System.out.println(p.getName());
        }
        
        System.out.println("//Mezők: ");
        for(Field f : b.getFields()){
            if(f.isProperty()){
                System.out.println("ingatlan");
            } else if(f.isService()){
                System.out.println("szolgaltatas");
            } else if(f.isLuck()){
                System.out.println("szerencse");
            } else{
                System.out.println("o.o");
            }
        }
        
        if(!dice.isEmpty()){
            System.out.println("//Dobások: ");
            for(int n : dice){
                System.out.print(n + " ");
                
            }
            System.out.println("");
        }
    }
    
    /**
     * Szimulálja a játékot, a szabályoknak megfelően.
     * @param fields  a táblán a mezők listája
     * @param players  a játékosok listája
     * @param losers  a kisett játékosok listája
     * @param diceRolls  a kockadobások listája
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  ha nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    public static void theGameIsOn(Board fields, ArrayList<Player> players, ArrayList<Player> losers, ArrayList<Integer> diceRolls) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException{
        while(players.size()> 1){
            int rollCounter = 0;
            for(Player p : players){
                p.step(diceRolls.get(rollCounter), fields);
                rollCounter++;
                
                if(!p.stillInGame()){
                    losers.add(p);
                }
            }
            
            for(Player l : losers){
                if(players.contains(l)){
                    players.remove(l);
                }
            }
        }
    }
    
    /**
     * Szimulálja a játékot, a szabályoknak megfelően.
     * @param fields  a táblán a mezők listája
     * @param players  a játékosok listája
     * @param losers  a kisett játékosok listája
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  ha nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    public static void theGameIsOn(Board fields, ArrayList<Player> players, ArrayList<Player> losers) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException{
        while(players.size()> 1){
            for(Player p : players){
                p.step(fields);
                
                if(!p.stillInGame()){
                    losers.add(p);
                }
            }
            
            for(Player l : losers){
                if(players.contains(l)){
                    players.remove(l);
                }
            }
        }
    }
    
    /**
     * Visszaadja a másodjára kieső játékost.
     * @param losers  a kiesett játékosok listája
     * @return  a másodjára kiesett játékost
     */
    public static String andTheSecondFromBehindIS(ArrayList<Player> losers){
        return losers.get(1).getName();
    }
    
    /**
     * Beolvas egy szöveget az inputról.
     * @param sc  a beolvasó scanner
     * @param msg  az üzenet
     * @return  inputra írt szöveg
     */
    public static String readString(Scanner sc, String msg){
        System.out.print(msg);
        return sc.nextLine();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException, SomethingWrongWithTheFileException, TooFewPlayersException, TooFewFieldsException {
        while(true){
            Scanner s = new Scanner(System.in);
            String input = readString(s, "Gimme that input file: ");
            
            Board fields = new Board();
            ArrayList<Player> players = new ArrayList<>();
            ArrayList<Integer> diceRolls = new ArrayList<>();
            ArrayList<Player> losers = new ArrayList<>();
            
            boolean allGood = true;

            try{
                createAll(fields, players, diceRolls, input);
            } catch(FileNotFoundException ex) {
                System.out.println("File not found! Next time double check for typos.");
                allGood = false;
            } catch (SomethingWrongWithTheFileException ex) {
                System.out.println("Something is waaay off with this file. Try another.");
                allGood = false;
            }
            //report(players, fields, diceRolls);
            if(allGood){
                System.out.println("Everybody's in.");
            
                try{
                    if(players.size() < 2){
                        throw new TooFewPlayersException();
                    }
                } catch(TooFewPlayersException e){
                    System.out.println("Come on, invite some friends!");
                    allGood = false;
                }

                try{
                    if(fields.getFields().size() < 2){
                        throw new TooFewFieldsException();
                    }
                } catch(TooFewFieldsException e){
                    System.out.println("Come on, do you want to play or not?!");
                    allGood = false;
                }

                if(allGood){
                    System.out.println("The game is about to start.");
                    try{
                        if(diceRolls.isEmpty()){
                            theGameIsOn(fields, players, losers);
                        } else
                            theGameIsOn(fields, players, losers, diceRolls);
                    } catch(AlreadyOwnedException e){
                        System.out.println("This Property already has an owner.");
                    } catch(PlayerAlreadyOutException e){
                        System.out.println("This player is already out of the game.");
                    } catch(NoOwnerException e){
                        System.out.println("This Property has no owner at the moment.");
                    } catch(DifferentOwnerException e){
                        System.out.println("This field is another player's property. You better pay up.");
                    } catch(HouseAlreadyThereException e){
                        System.out.println("There already is a house.");
                    }

                    String secondFromBehind = andTheSecondFromBehindIS(losers);
                    System.out.println("And the second loser is " + secondFromBehind);
                }
            }
            
            boolean goodAnswer = false;
            String again = "";
            
            while(!goodAnswer){
                again = readString(s, "Do you want to go on? (yes/no): ");
                
                if("yes".equals(again)){
                    goodAnswer = true;
                } else if("no".equals(again)){
                    goodAnswer = true;
                }
            }
            
            if("no".equals(again)){
                System.out.println("The program stops.");
                break;
            }
        }
    }
}
