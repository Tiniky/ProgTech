/**
 *
 * @author RDDZXA
 * 
 * Az osztály a játák tesztelését reprezentálja.
 */
public class Test {
    public static void main(String[] args) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException{
        /*//T1: Rálép a szerencse mezőre
        Field start = new Luck(2000);
        Field Luck = new Luck (300);
        Board board = new Board();
        board.increase(start);
        board.increase(Luck);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */
        
        /*//T2: Rálép a szolgáltatás mezőre
        Field start = new Luck(2000);
        Field Service = new Service (500);
        Board board = new Board();
        board.increase(start);
        board.increase(Service);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */
        
        /*//T3: Rálép az ingatlan mezőre
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */
        
        /*//T4: Rálép az ingatlan mezőre, ami már az övé
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(2, board);
        System.out.println(p1);
        */
        
        /*//T5: Mohó játékos stratégiájának szemléltetése
        Field start = new Luck(2000);
        Field Property = new Property();
        Field Property2 = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        board.increase(Property2);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */
        
        /*//T6: Taktikus játékos stratégiájának szemléltetése
        Field start = new Luck(2000);
        Field Property = new Property();
        Field Property2 = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        board.increase(Property2);
        
        Player p1 = new Tactician("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */
        
        /*//T7: Óvatos játékos stratégiájának szemléltetése
        Field start = new Luck(2000);
        Field Property = new Property();
        Field Property2 = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        board.increase(Property2);
        
        Player p1 = new Cautious("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        */

        /*//T8: Rálép valakinek az ingatlanára
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        Player p2 = new Greedy("Funonymus", board.start());
        System.out.println(p2);
        
        p1.step(1, board);
        System.out.println(p1);
        System.out.println(p2);
        
        p2.step(1, board);
        System.out.println(p1);
        System.out.println(p2);
        */
        
        /*//T9: Rálép valakinek az ingatlanára, van ház is az ingatlanon
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        Player p2 = new Greedy("Funonymus", board.start());
        System.out.println(p2);
        
        p1.step(1, board);
        p1.step(2, board);
        System.out.println(p1);
        System.out.println(p2);
        
        p2.step(1, board);
        System.out.println(p1);
        System.out.println(p2);
        */
        
        /*//T10: Játkos kiesett de még lépni akar
        Field start = new Luck(2000);
        Field Property = new Property();
        Field Property2 = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        board.increase(Property2);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(2, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        try{
            p1.step(1, board);
        } catch(PlayerAlreadyOutException e){
            System.out.println("This player is already out of the game.");
        }
        */
        
        /*//T11: Már megvette a telket de meg próbálja venni megint
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        try{
            p1.buyProperty(Property);
        } catch(AlreadyOwnedException e){
            System.out.println("This Property already has an owner.");
        }
        */
        
        /*//T12: Már vett házat a telekre de meg próbálja venni megint
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        p1.step(2, board);
        System.out.println(p1);
        
        try{
            p1.buyHouse(Property);
        } catch(HouseAlreadyThereException e){
            System.out.println("There already is a house.");
        }
        */
        
        /*//T13: Egy másik játékos házát próbálja megvenni
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        Player p2 = new Greedy("Funonymus", board.start());
        System.out.println(p2);
        
        p1.step(1, board);
        System.out.println(p1);
        
        try{
            p2.buyHouse(Property);
        } catch(DifferentOwnerException e){
            System.out.println("This field is another player's property. You better pay up.");
        }
        */
        
        /*//T14: Egy olyan telekre próbál házat venni, aminek nincs tulajdonosa
        Field start = new Luck(2000);
        Field Property = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        try{
            p1.buyHouse(Property);
        } catch(NoOwnerException e){
            System.out.println("This Property has no owner at the moment.");
        }
        */
        
        //T15: A telkek felszabadulnak ha a játékos kiesik
        Field start = new Luck(2000);
        Field Property = new Property();
        Field Property2 = new Property();
        Board board = new Board();
        board.increase(start);
        board.increase(Property);
        board.increase(Property2);
        
        Player p1 = new Greedy("Tiniky", board.start());
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        p1.step(2, board);
        System.out.println(p1);
        
        p1.step(1, board);
        System.out.println(p1);
        
        if(!p1.stillInGame()){
            System.out.println("Player too greedy, lost everything..");
            System.out.println(p1);
        }
        
        Player p2 = new Greedy("Funonymus", board.start());
        System.out.println(p2);
        
        p2.step(1, board);
        System.out.println(p2);
        
    }
}
