package lowbudgetminirpg;

/**
 * 
 * @author RDDZXA
 */
public class Block {
    public BlockType type;
    public Position pos;

    public Block(BlockType type, int row, int col) {
        this.type = type;
        this.pos = new Position(row, col);
    }
    
    public Position getPos(){
        return this.pos;
    }
    
    public void changeToEmptyTile(){
        type = BlockType.FLOOR;
    }
    
}
