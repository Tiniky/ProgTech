/**
 *
 * @author RDDZXA
 * 
 * Az osztály egy szerencse mezőt reprezentál.
 */
public class Luck extends Field {

    /**
     * A szerencse mező jutalma.
     */
    protected final int prize;

    /**
     * Létrehoz egy szerencse mezőt és beállítja a jutalmat a megadott paraméterre.
     * @param prize  jutalom
     */
    public Luck(int prize) {
        this.prize = prize;
    }

    /**
     * Megnézi, hogy a mező szerencse-e.
     * @return mindig igazzal tér vissza
     */
    @Override
    public boolean isLuck() {return true;}
    
    /**
     * Visszaadja a mező jutalmát.
     * @return jutalom
     */
    @Override
    public int getPrize() {
        return prize;
    }
}
