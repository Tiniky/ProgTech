package lowbudgetminirpg;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

/**
 *
 * @author RDDZXA
 */
public class Game {
    private final HashMap<Difficulty, GameLevel> gameLevels;
    private GameLevel gameLevel = null;
    
    public Game() {
        gameLevels = new HashMap<>();
        createLevels();
    }

    public void loadGame(GameID gameID){
        gameLevel = new GameLevel(gameID, gameLevels.get(gameID.getDiff()).game);
    }
    
    public void step(Direction d){
        gameLevel.movePlayer(d);
    }
    
    public void enemyStep(){
        for(Enemy mob : gameLevel.enemies){
            mob.step();
        }
    }
    
    public void updateEnemyPos(){ gameLevel.updatePositions();}
    
    public void meetUpCheck(){
        ArrayList<Position> mobs = getEnemyPos();
        Position player = getPlayerPos();
        for(Position mob : mobs){
            if(mob.equals(player)){
                gameLevel.player.death();
            }
        }
    }
    
    public ArrayList<Difficulty> getDifficulties(){ return new ArrayList<>(EnumSet.allOf(Difficulty.class)); }
    public int getMobsOfDifficulty(GameID gameID){
        if (!gameLevels.containsKey(gameID.getDiff())) return 0;
        return gameID.getEnemyNum();
    }
    
    public boolean isLevelLoaded(){ 
        return gameLevel != null; 
    }
    public GameID getGameID(){ return (gameLevel != null) ? gameLevel.gameID : null; }
    
    public int getLevelRows(){ return gameLevel.rows; }
    public int getLevelCols(){ return gameLevel.cols; }
    public Block getTile(int row, int col){ return gameLevel.level[row][col]; }
    public boolean isTileValid(Position pos) { return gameLevel.isValidPosition(pos); }
    public boolean stillGoing () { return gameLevel.player.IsStillAlive(); }
    public int getCollectedNum () { return gameLevel.player.getTreasurePickedUp(); }
    public Position getPlayerPos(){ return gameLevel.player.getPos(); }
    public Direction getPlayerDir(){ return gameLevel.player.getCurrentDir(); }
    public Block getPlayerTile(){ 
        Position pPos = getPlayerPos();
        return gameLevel.level[pPos.y][pPos.x];
    }
    
    public ArrayList<Position> getEnemyPos(){return gameLevel.positions;}
    
    public Position getCollectiblePos(){
        Position newPos = null;
        for(int i=0; i<gameLevel.rows; i++){
            for(int j=0; j<gameLevel.cols; j++){
                if(gameLevel.level[i][j].type == BlockType.COLLECTIBLE){
                    newPos = new Position(i,j);
                }
            }
        }
        return newPos;
    }
    
    private void addNewGameLevel(GameLevel gameLevel){
       GameLevel levelsOfDifficulty;
        if(!gameLevels.containsKey(gameLevel.gameID.diff)){
            levelsOfDifficulty = new GameLevel(gameLevel.gameID, gameLevel.game);
            gameLevels.put(gameLevel.gameID.diff, levelsOfDifficulty);
        }
    }
    
    private void createLevels(){
        ArrayList<Difficulty> difficulties = new ArrayList<>(EnumSet.allOf(Difficulty.class));
        int diffz = difficulties.size();

        int n = 0;
        while (n < diffz){
            GameID id = new GameID(difficulties.get(n));
            addNewGameLevel(new GameLevel(id, this));
            n++;
        }
    }
}
