/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor már a játlkos kiesett, de lépni próbál.
 */
public class TooFewPlayersException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public TooFewPlayersException(){
        super();
    }
    
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public TooFewPlayersException(String msg){
        super(msg);
    }
}
