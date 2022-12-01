/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor még nincs tulajdonos az ingatlan mezőnek.
 */
public class NoOwnerException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public NoOwnerException(){
        super();
    }
    
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public NoOwnerException(String msg){
        super(msg);
    }
}