
/**
 *
 * @author RDDZXA
 * 
 * Az osztály a játéko szimulálja.
 */
public class Game {
    /**
     * A pálya mérete.
     */
    private int size;
    /**
     * A játékos, aki most fog lépni.
     */
    private Player current;
    /**
     * A pálya.
     */
    private Player[][] table;
    /**
     * A pálya üres mezői.
     */
    private int empty;
    
    /**
     * Létrehozza a pályát.
     * Mindig az X fog kezdeni.
     * Az üres mező a pálya méretének négyzete.
     * Feltöltjük NONE-al a pályát.
     * @param size  pálya mérete
     */
    public Game(int size){
        this.size = size;
        this.current = Player.X;
        this.empty = size*size;
        
        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NONE;
            }
        }
    }
    
    /**
     * A játékos elfoglalja az adott koordinátákon levő mezőt.
     * Az üres mezők száma csökken.
     * @param row  sor
     * @param column  oszlop
     * @return  játákos
     */
    public Player step(int row, int column){
        if(table[row][column] != Player.NONE){
            return table[row][column];
        }
        
        table[row][column] = this.current;
        empty--;
        
        if(current == Player.X){
            current = Player.O;
        } else {
            current = Player.X;
        }
            
        return table[row][column];
    }
    
    /**
     * Megnézi, hogy kigyűlt-e 5 összeköthető mező.
     * @param row  sor
     * @param col  oszlop
     * @param size  méret
     * @return  kigyűlt az 5
     */
    public boolean gameIsWon(int row, int col, int size){
        if(table[row][col] == Player.NONE){
            return false;
        } else{
            if (count(table[row][col], row, col, 1, 0, size) >= 5)
               return true;
            if (count(table[row][col], row, col, 0, 1, size) >= 5)
               return true;
            if (count(table[row][col], row, col, 1, -1, size) >= 5)
               return true;
            return count(table[row][col], row, col, 1, 1, size) >= 5;
        }
    }
    
    /**
     * Visszaállítja NONE-ra az adott koordinátán lévő mezőt.
     * Az üres mezők száma megnő.
     * @param row  sor
     * @param col  oszlop
     */
    public void prank(int row, int col){
        table[row][col] = Player.NONE;
        empty++;
    }
    
    /**
     * Megnézi van-e elég egymás melletti mező ahhoz, hogy kiszúrjon a játékossal.
     * @param row  sor
     * @param col  oszlop
     * @param size  méret
     * @return  hányszor lehet kiszúrni
     */
    public int shouldIPrank(int row, int col, int size){
        int a = count(table[row][col], row, col, 1, 0, size);
        int b = count(table[row][col], row, col, 0, 1, size);
        int c = count(table[row][col], row, col, 1, -1, size);
        int d = count(table[row][col], row, col, 1, 1, size);
        
        if(a == 4 || b == 4 || c == 4 || d == 4){
            return 2;
        } else if(a == 3 || b == 3 || c == 3 || d == 3){
            return 1;
        }
        
        return 0;
    }
    
    /**
     * Megszámolja hány összeköthető mező van.
     * @param player
     * @param row  sor
     * @param col  oszlop
     * @param X  kezdőpont X koordinátája
     * @param Y  kezdőpont Y koordinátája
     * @param size  méret
     * @return  összeköthető mezők száma
     */
    private int count(Player player, int row, int col, int X, int Y, int size){
        int db = 1;
        
        int r,c;
        r = row + X;
        c = col + Y;
        
        while(r >= 0 && r < size && c >= 0 && c < size && table[r][c] == player){
            db++;
            r += X;
            c += Y;
        }
        
        r = row - X;
        c = col - Y;
        while(r >= 0 && r < size && c >= 0 && c < size && table[r][c] == player){
            db++;
            r -= X;
            c -= Y;
        }
        
        return db;
    }
    
    /**
     * Visszaadja a soron következő játékost.
     * @return  soron következő játékos
     */
    public Player getCurrentPlayer(){
        return current;
    }
    
    /**
     * Visszaadja hány üres mező van.
     * @return  üres mezők száma
     */
    public int getEmpty(){
        return empty;
    }
}
