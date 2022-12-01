/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor már van ház az ingatlan mezőn.
 */
public class HouseAlreadyThereException extends Exception{

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public HouseAlreadyThereException(){
        super();
    }

    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public HouseAlreadyThereException(String msg){
        super(msg);
    }
}
