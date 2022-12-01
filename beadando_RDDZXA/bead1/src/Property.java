/**
 *
 * @author RDDZXA
 * 
 * Az osztály egy ingatlan mezőt reprezentál.
 */
public class Property extends Field {

    /**
     * Az ingatlan tulajdonosa.
     */
    protected Player owner;

    /**
     * Bool, ami akkor igaz, ha van ház az birtokon.
     */
    protected boolean hasHouse;

    /**
     * Létrehoz egy ingatlan mezőt.
     * Kezdetben nincs tulajdonosa és ház sincs rajta.
     */
    public Property() {
        this.owner = null;
        this.hasHouse = false;
    }

    /**
     * Megnézi, hogy a mező ingatlan-e.
     * @return mindig igazzal tér vissza
     */
    @Override
    public boolean isProperty() {return true;}
    
    /**
     * Megnézi, hogy van-e tulajdonosa az adott ingatlannak.
     * @return igaz, ha van
     */
    @Override
    public boolean isOwned(){
        return this.owner != null;
    }
    
    /**
     * Visszaadja a mező tulajdonosát, ha van neki.
     * @return a tulajdonos játékos
     * @throws NoOwnerException  ha nincs birtokosa a mezőnek
     */
    @Override
    public Player getOwner() throws NoOwnerException {
        if(this.isOwned())
            return this.owner;
        else
            throw new NoOwnerException("This Property has no owner at the moment.");
    }

    /**
     * Beállítja a mező tulajdonosát.
     * @param p  a játékos, aki meg akarja venni
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     */
    @Override
    public void setOwner(Player p) throws AlreadyOwnedException {
        if(!this.isOwned())
            this.owner = p;
        else
            throw new AlreadyOwnedException("This Property already has an owner.");
    }

    /**
     * Az adott mezőn van-e már ház.
     * @return igaz, ha a van rajta állítás hamis
     */
    @Override
    public boolean isEmpty() {
        return !this.hasHouse;
    }

    /**
     * Hozzáad egy házat a mezőre.
     * Igaz lesz a van rajta ház állítás
     */
    @Override
    public void buildHouse() {
        this.hasHouse = true;
    }
    
    /**
     * Kezdetleges helyzetre állítja a mezőt.
     * Törli a tulajt és hamisra állítja a van rajta ház állítást.
     */
    @Override
    public void reset(){
        this.owner = null;
        this.hasHouse = false;
    }
}
