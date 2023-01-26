package lowbudgetminirpg;

/**
 *
 * @author RDDZXA
 */
public class Player {
    private Position position;
    private int treasurePickedUp;
    private boolean isAlive;
    private Direction currentDir;

    public Player() {
        this.position = new Position(12,12);
        this.treasurePickedUp = 0;
        this.isAlive = true;
        this.currentDir = Direction.DOWN;
    }

    public Direction getCurrentDir() {
        return this.currentDir;
    }

    public void setCurrentDir(Direction currentDir) {
        this.currentDir = currentDir;
    }
    
    public Position getPos() {
        return new Position(position.x, position.y);
    }

    public void setPos(Position pos) {
        this.position = pos;
    }

    public int getTreasurePickedUp() {
        return this.treasurePickedUp;
    }

    public void justPickedUpOne() {
        this.treasurePickedUp++;
    }

    public boolean IsStillAlive() {
        return isAlive;
    }

    public void death() {
        this.isAlive = false;
    }
}
