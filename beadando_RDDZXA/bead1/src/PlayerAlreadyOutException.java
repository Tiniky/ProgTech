/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor már a játlkos kiesett, de lépni próbál.
 */
public class PlayerAlreadyOutException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public PlayerAlreadyOutException(){
        super();
    }
    
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public PlayerAlreadyOutException(String msg){
        super(msg);
    }
}
