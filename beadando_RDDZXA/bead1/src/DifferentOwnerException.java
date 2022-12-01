/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor másik játékos a tulajdonosa az ingatlan mezőnek.
 */
public class DifferentOwnerException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public DifferentOwnerException(){
        super();
    }
    
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public DifferentOwnerException(String msg){
        super(msg);
    }
}