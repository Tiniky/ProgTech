/**
 *
 * @author RDDZXA
 * 
 * Az osztály egy koordináta párt reprezentál.
 */
public class Coordinate<X, Y>{
    /**
     * X koordináta
     */
    private final int x;
    /**
     * Y koordináta
     */
    private final int y;

        /**
         * Létrehoz egy koordinátát.
         * @param x  X koordináta
         * @param y  Y koordináta
         */
        public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja az X koordinátát.
     * @return  X koordináta
     */
    public int getX() {return x;}
    /**
     * Visszaadja az Y koordinátát. 
     * @return   Y koordináta
     */
    public int getY() {return y;}

 }