
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author RDDZXA
 * 
 * Az osztály egy játékost reprezentál.
 */
public class Player {
    
    /**
     * A játékos neve.
     */
    protected String name;

    /**
     * A játékos tőkéje Petákban.
     */
    protected int coin;

    /**
     * A mező, amin a játékos áll.
     */
    protected Field standpoint;

    /**
     * A játékos birtokainak listája.
     */
    protected ArrayList<Field> properties;

    /**
     * Létrehoz egy játékost a megadott névvel, és az adott mezőre állítja.
     * A játékosok 10000 Petákkal kezdenek.
     * @param name  név
     * @param f  mező, ahol áll
     */
    public Player(String name, Field f) {
        this.name = name;
        this.coin = 10000;
        this.standpoint = f;
        this.properties = new ArrayList<>();
    }
    
    /**
     * Visszaadja a játékos nevét
     * @return Játékos neve
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Megnézi mennyi Petákkal rendelkezik a játékos.
     * Ha több van, mint 0, akkor még játékban van.
     * Másképp kiesik és elveszti minden birtokát.
     * A birtokok újra megvásárolhatóvá válnak.
     * @return játékban van-e még az adott játékos
     */
    public boolean stillInGame(){
        if(this.coin > 0)
            return true;
        else
            this.losesALL();
        
        return false;
    }
    
    /**
     * Ha még játékban van a játékos megveszi az ingatlant.
     * Ő lesz a mező tulajdonosa.
     * Levonódik tőle az ingatlan ára.
     * A mező hozzáadódik a játékos birtokainak listájához.
     * @param p  ingatlan mező
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     */
    public void buyProperty(Field p) throws PlayerAlreadyOutException, AlreadyOwnedException{
        if(this.stillInGame()){
            p.setOwner(this);
            this.coin -= 1000;
            this.properties.add(p);
        } else
            throw new PlayerAlreadyOutException("This player is already out of the game.");
    }
    
    /**
     * Ha még játékban van a játékos és ő az ingatlan birtokosa, akkor házat vesz rá.
     * Levonódik tőle a ház ára.
     * @param p ingatlan mező
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws NoOwnerException  ha nincs birtokosa a mezőnek
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    public void buyHouse(Field p) throws PlayerAlreadyOutException, DifferentOwnerException, NoOwnerException, HouseAlreadyThereException{
        if(this.stillInGame()){
            if(p.isEmpty()){
                if(p.getOwner().equals(this)){
                    this.coin -= 4000;
                    p.buildHouse();
                } else if(p.isOwned()){
                    throw new DifferentOwnerException("This field is another player's property. You better pay up.");
                }
            } else
                throw new HouseAlreadyThereException("There already is a house.");    
        } else
            throw new PlayerAlreadyOutException("This player is already out of the game.");
    }
    
    /**
     * Ha még játékban van a játékos, akkor kifizeti a szolgáltatás árát.
     * Levonódik tőle a szolgáltatási költség.
     * @param s  szolgáltatás mező
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     */
    public void payForService(Field s) throws PlayerAlreadyOutException{
        if(this.stillInGame()){
            this.coin -= s.getPrice();
        } else
            throw new PlayerAlreadyOutException("This player is already out of the game.");
    }
    
    /**
     * Ha még játékban van a játékos, akkor fizet a másik játékosnak, akinek a birtokára lépett.
     * Ha az ingatlan mezőn nincs ház 500 Peták vonódik le tőle.
     * Ha az ingatlan mezőn van ház 2000 Peták vonódik le tőle.
     * @param p  az ingatlan tulajdonosa
     * @param f  ingatlan mező
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     */
    public void payToPlayer(Player p, Field f) throws PlayerAlreadyOutException{
        if(this.stillInGame()){
            if(f.isEmpty()){
                p.coin += 500;
                this.coin -= 500;
            } else{
                p.coin += 2000;
                this.coin -= 2000;
            } 
        } else
            throw new PlayerAlreadyOutException("This player lost all his Peták. Sorry buddy, you're out.");
    }
    
    /**
     * Ha a játékos még játékban van, akkor megkapja a szerencs mező bónuszát.
     * Hozzáadódik a bónusz összege a játékos tőkéjéhez.
     * @param l  szerencse mező
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     */
    public void claimTheLuck(Field l) throws PlayerAlreadyOutException{
        if(this.stillInGame()){
            this.coin += l.getPrize();
        } else
            throw new PlayerAlreadyOutException("This player is already out of the game.");
    }
    
    /**
     * Végigmegy a birtokok listáján és sorra reseteli őket.
     */
    protected void losesALL(){
        for(Field p : this.properties){
            p.reset();
        }
        this.properties.clear();
    }
    
    /**
     * Random dob egy számot 1 és 6 között.
     * @return  1 és 6 közötti érték
     */
    public int roll(){
        Random rand = new Random();
        return rand.nextInt(6 - 1 + 1) + 1;
    }
    
    /**
     * A játékos lép egyet a dobott értéknek megfelelően.
     * Megkeressük pontosan hol áll a játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * @param n  dobott szám
     * @param b  tábla
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  a nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    public void step(int n, Board b) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException {
        int index = b.getFields().indexOf(this.standpoint);
        this.standpoint = b.stepTo(index, n);
    }
    
    /**
     * A játékos lép egyet a dobott értéknek megfelelően.
     * A játékos először dob egyet.
     * Megkeressük pontosan hol áll a játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * @param b  tábla
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  a nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    public void step(Board b) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException {
        int n = roll();
        int index = b.getFields().indexOf(this.standpoint);
        this.standpoint = b.stepTo(index, n);
    }
    
    /**
     * Átalakítja az adott objektumot számokká.
     * @return  a játékos hash értéke
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + this.coin;
        hash = 19 * hash + Objects.hashCode(this.standpoint);
        hash = 19 * hash + Objects.hashCode(this.properties);
        return hash;
    }

    /**
     * Eldönti, hogy két játékos ugyanaz-e.
     * @param obj  összehasonlítandó Játékos
     * @return  ugyanaz a játékos
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.coin != other.coin) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.standpoint, other.standpoint)) {
            return false;
        }
        return Objects.equals(this.properties, other.properties);
    }
    
    @Override
    public String toString(){
        int house = 0;
        for(Field p : properties){
            if(!p.isEmpty()){
                house++;
            }
        }
        return "Játékos neve : " + this.name + ", pénze: " + this.coin + ", telkeinek száma: " + this.properties.size() + ", házak száma: " + house;
    }
}
