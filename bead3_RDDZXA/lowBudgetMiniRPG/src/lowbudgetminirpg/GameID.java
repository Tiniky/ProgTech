package lowbudgetminirpg;

import java.util.Objects;

/**
 *
 * @author RDDZXA
 */
public class GameID {
    public Difficulty diff;
    public int enemyNum;

    public GameID(Difficulty difficulty) {
        this.diff = difficulty;
        
        switch(this.diff){
            case EASY -> this.enemyNum = 4;
            case MEDIUM -> this.enemyNum = 5;
            case HARD -> this.enemyNum = 7;
            case EXPERT -> this.enemyNum = 10;
            case LEGENDARY -> this.enemyNum = 15;
        }
    }

    public Difficulty getDiff() {
        return diff;
    }

    public int getEnemyNum() {
        return enemyNum;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.diff);
        hash = 37 * hash + this.enemyNum;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameID other = (GameID) obj;
        if (this.enemyNum != other.enemyNum) {
            return false;
        }
        if (!Objects.equals(this.diff, other.diff)) {
            return false;
        }
        return true;
    }

    
    
    
}
