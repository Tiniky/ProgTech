/**
 *
 * @author RDDZXA
 * 
 * Az osztály a szolgáltatás mezőt reprezentálja.
 */
public class Service extends Field {

    /**
     * A szolgáltatás ára.
     */
    protected final int price;

    /**
     * Létrehoz egy szolgáltatást és beállítja az árát a megadott paraméterre.
     * @param price  ár
     */
    public Service(int price) {
        this.price = price;
    }
    
    /**
     * Megnézi, hogy a mező szolgáltatás-e.
     * @return mindig igazzal tér vissza
     */
    @Override
    public boolean isService() {return true;}

    /**
     * Visszaadja a mező költségét.
     * @return költség
     */
    @Override
    public int getPrice() {
        return price;
    }
}
