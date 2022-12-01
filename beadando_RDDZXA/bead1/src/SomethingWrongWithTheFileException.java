/**
 *
 * @author RDDZXA
 * 
 * Az osztály azt a kivételt reprezentálja, amikor valami nem stimmel a beolvasandó fájllal.
 */
public class SomethingWrongWithTheFileException extends Exception{
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     */
    public SomethingWrongWithTheFileException(){
        super();
    }
    
    /**
     * Létrehozza a kivételt, meghívja az Exception konstruktorát.
     * Kiírja a stdoutra a megadott üzenetet.
     * @param msg  üzenet
     */
    public SomethingWrongWithTheFileException(String msg){
        super(msg);
    }
}
