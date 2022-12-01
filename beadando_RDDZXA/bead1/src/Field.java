/**
 *
 * @author RDDZXA
 * 
 * A abstract ősosztály egy mezőt reprezentál.
 */
public abstract class Field {
    
    /**
     * Megnézi, hogy a mező ingatlan-e.
     * @return igaz, ha ingatlan
     */
    public boolean isProperty() {return false;}

    /**
     * Megnézi, hogy a mező szolgáltatás-e.
     * @return igaz, ha szolgáltatás
     */
    public boolean isService() {return false;}

    /**
     * Megnézi, hogy a mező szerencse-e.
     * @return igaz, ha szerencse
     */
    public boolean isLuck() {return false;}
    
    /**
     * Megnézi, hogy van-e tulajdonosa a mezőnek.
     * @return igaz, ha van
     */
    protected boolean isOwned() {return false;}
    
    /**
     * Beállítja a mező tulajdonosát.
     * @param owner  a játékos, aki meg akarja venni
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     */
    protected void setOwner(Player owner) throws AlreadyOwnedException {
        if(this.isOwned())
            throw new AlreadyOwnedException("This Property already has an owner.");
    }
    
    /**
     * Az adott mezőn van-e már ház.
     * @return üres a mező
     */
    protected boolean isEmpty() {
        return false;
    }
    
    /**
     * Visszaadja a mező tulajdonosát, ha van neki.
     * @return a tulajdonos játékos
     * @throws NoOwnerException  ha nincs birtokosa a mezőnek
     */
    protected Player getOwner() throws NoOwnerException {
        if(this.isOwned())
            return null;
        else
            throw new NoOwnerException("This Property has no owner at the moment.");
    }
    
    /**
     * Visszaadja a mező költségét.
     * @return költség
     */
    protected int getPrice() {
        return 0;
    }
    
    /**
     * Visszaadja a mező jutalmát.
     * @return jutalom
     */
    protected int getPrize() {
        return 0;
    }
    
    /**
     * Kezdetleges helyzetre állítja a mezőt.
     */
    protected void reset() {}
    
    /**
     * Hozzáad egy házat a mezőre.
     */
    public void buildHouse() {}
}
