package lowbudgetminirpg;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author RDDZXA
 */
public final class GameLevel {
    public final GameID gameID;
    public final int rows = 25;
    public final int cols = 25;
    public final Block[][] level = new Block[rows][cols];
    private final Random rand = new Random();
    public Player player = new Player();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Position> positions = new ArrayList<>();   //az elso elem a jatekos pozicioja
    public final Game game;
    public Position treasure = null;
    
    public GameLevel(GameID gameID, Game game){
        this.gameID = gameID;
        this.game = game;
        
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(i==0 || i==24 || j==0 || j == 24){
                    level[i][j] = new Block(BlockType.WALL, i, j);
                } else{
                    level[i][j] = new Block(BlockType.FLOOR, i, j);
                }
            }
        }
        
        spawnCollectible();
        spawnMob();
        
        updatePositions();
    }
    
    public void spawnMob(){
        int spawned = this.gameID.getEnemyNum();
        Position placeToPut;
        while(spawned > 0){
            placeToPut = getRandomPos();
            if(isFree(placeToPut)){
                enemies.add(new Enemy(placeToPut, game));
                spawned--;
            }
        }
    }
    
    public void spawnCollectible(){
        boolean spawned = false;
        Position placeToPut;
        while(!spawned){
            placeToPut = getRandomPos();
            if(isFree(placeToPut)){
                treasure = new Position(placeToPut.x, placeToPut.y);
                level[treasure.x][treasure.y] = new Block(BlockType.COLLECTIBLE, treasure.x, treasure.y);
                spawned = true;
            }
        }
    }
    
    public Position getRandomPos(){
        int randomX = rand.nextInt(23) + 1;
        int randomY = rand.nextInt(23) + 1;
        
        return new Position(randomX, randomY);
    }
    
    public void updatePositions(){
        this.positions.clear();
        for(Enemy mob: this.enemies){
            this.positions.add(mob.getPos());
        }
    }
    
    public boolean isValidPosition(Position p){
        return (p.x > 0 && p.y > 0 && p.x < cols-1 && p.y < rows-1);
    }
    
    public boolean isFree(Position p){
        boolean isEmpty = true;
        
        for(Position pos: positions){
            if(pos.equals(p)){
                isEmpty = false;
            }
        }
        
        return isValidPosition(p) && isEmpty;
    }
    
    public boolean movePlayer(Direction d){
        Position nextPos = player.getPos().translate(d);
        if (isValidPosition(nextPos)) {
            player.setPos(nextPos);
            player.setCurrentDir(d);
            Block tile = level[nextPos.y][nextPos.x];
            if(tile.type == BlockType.COLLECTIBLE){
                player.justPickedUpOne();
                tile.changeToEmptyTile();
                spawnCollectible();
            }
            return true;
        } 
        return false;
    }

}
