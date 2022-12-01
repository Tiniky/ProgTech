import java.util.ArrayList;

/**
 *
 * @author RDDZXA
 * 
 * A játék táblát reprezentálja.
 */
public class Board {

    /**
     * A tábla mezőinek listája.
     */
    protected ArrayList<Field> fields;

    /**
     * A mezők listájának hossza.
     */
    protected int length;
    
    /**
     * Létrehoz egy táblát egy üres listával.
     */
    public Board(){
        this.fields = new ArrayList<>();
        this.length = 0;
    }
    
    /**
     * Hozzáad egy mezőt a mezők listájához.
     * @param f  mező
     */
    protected void increase(Field f){
        this.fields.add(f);
        this.length++;
    }
    
    /**
     * Visszaadja a tábla a mezők listáját.
     * @return mezők listája
     */
    public ArrayList<Field> getFields(){
        return this.fields;
    }
    
    /**
     * A tábla első mezőjét adja vissza.
     * @return az első mezőt a listából
     */
    public Field start(){
        if(this.length > 0){
            return this.fields.get(0);
        }
        return null;
    }
    
    /**
     * Áthelyezi a játékosokat egy új mezőre.
     * Ha túlindexel előlröl kezdi.
     * @param index  ahol jelenleg van
     * @param roll  a szám amit dobott
     * @return a mezőt ahova lépett
     */
    protected Field stepTo(int index, int  roll){
        if(index + roll < this.length){
            return this.fields.get(index + roll);
        } else
            return this.fields.get(index + roll - this.length);
    }
}
