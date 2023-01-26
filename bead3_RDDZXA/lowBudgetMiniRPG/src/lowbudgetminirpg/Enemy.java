package lowbudgetminirpg;

import java.util.Random;

/**
 * 
 * @author RDDZXA
 */
public class Enemy {
    protected Position pos;
    protected Game game;
    protected Direction lastDir = null;
    private final Random rand = new Random();

    public Enemy(Position pos, Game game) {
        this.pos = pos;
        this.game = game;
    }
    
    public void step(){
        boolean stepped = false;
        
        while(!stepped){
            if(lastDir == null){
                this.lastDir = getRandomDir();
                
                Position nextPos = this.pos.translate(lastDir);

                if(isTileGood(nextPos)){
                    this.pos = nextPos;
                    stepped = true;
                }
            } else{
                Position maybeNextPos = this.pos.translate(lastDir);
                if(isTileGood(maybeNextPos)){
                    this.pos = maybeNextPos;
                    stepped = true;
                } else{
                    switch(lastDir){
                        case DOWN ->{
                            lastDir = Direction.UP;
                        }
                        case LEFT ->{
                            lastDir = Direction.RIGHT;
                        }
                        case RIGHT ->{
                            lastDir = Direction.LEFT;
                        }
                        case UP ->{
                            lastDir = Direction.DOWN;
                        }
                    }
                    
                    Position nextPos = this.pos.translate(lastDir);

                    if(isTileGood(nextPos)){
                        this.pos = nextPos;
                        stepped = true;
                    }
                }
            }
        }
    }
    
    public Direction getRandomDir(){
        int randomN = rand.nextInt(4);
        
        switch(randomN){
            case 0 ->{
                return Direction.UP;
            }
            case 1 ->{
                return Direction.RIGHT;
            }
            case 2 ->{
                return Direction.LEFT;
            }
            case 3 ->{
                return Direction.DOWN;
            }
        }
        return null;
    }
    
    public Position getPos() {
        if(this.pos == null){
            System.out.println("what");
        }
        return new Position(pos.x, pos.y);
    }
    
    protected boolean isTileGood(Position pos){
        return game.isTileValid(pos);
    }
}
