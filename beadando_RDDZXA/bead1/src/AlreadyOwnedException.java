/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor már van tulajdonos az ingatlan mezőnek.
 */
public class AlreadyOwnedException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public AlreadyOwnedException(){
        super();
    }

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public AlreadyOwnedException(String msg){
        super(msg);
    }
}
