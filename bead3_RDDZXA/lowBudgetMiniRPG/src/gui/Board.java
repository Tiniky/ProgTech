package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import lowbudgetminirpg.Game;
import lowbudgetminirpg.BlockType;
import lowbudgetminirpg.Position;
import res.ResourceLoader;

/**
 *
 * @author RDDZXA
 */
public class Board extends JPanel {
    private Game game;
    private final Image wall, floor, treasure, playerFront, playerBack, playerLeft, playerRight, mob, wizard;
    private double scale;
    private int scaled_size;
    private final int tile_size = 32;
    
    public Board(Game g) throws IOException{
        game = g;
        scale = 1.0;
        scaled_size = (int)(scale * tile_size);
        floor = ResourceLoader.loadImage("res/empty.png");
        wall = ResourceLoader.loadImage("res/wall.png");
        treasure = ResourceLoader.loadImage("res/treasure2.png");
        playerFront = ResourceLoader.loadImage("res/player.png");
        playerBack = ResourceLoader.loadImage("res/playerBack.png");
        playerLeft = ResourceLoader.loadImage("res/playerLeft.png");
        playerRight = ResourceLoader.loadImage("res/playerRight.png");
        mob = ResourceLoader.loadImage("res/mob2.png");
        wizard = ResourceLoader.loadImage("res/wizard.png");
    }
    
    public boolean setScale(double scale){
        this.scale = scale;
        scaled_size = (int)(scale * tile_size);
        return refresh();
    }
    
    public boolean refresh(){
        if (!game.isLevelLoaded()) return false;
        Dimension dim = new Dimension(game.getLevelCols() * scaled_size, game.getLevelRows() * scaled_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!game.isLevelLoaded()) return;
        
        Graphics2D gr = (Graphics2D)g;
        int w = game.getLevelCols();
        int h = game.getLevelRows();
        Position adventurer = game.getPlayerPos();
        ArrayList<Position> mobs = game.getEnemyPos();
        
        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                Image img = null;
                BlockType bt = game.getTile(y, x).type;
                switch (bt){
                    case WALL -> img = wall;
                    case FLOOR -> img = floor;
                    case COLLECTIBLE -> img = treasure;
                }
                
                if(adventurer.x == x && adventurer.y==y){
                    switch(game.getPlayerDir()){
                        case UP -> img = playerBack;
                        case DOWN -> img = playerFront;
                        case LEFT -> img = playerLeft;
                        case RIGHT -> img = playerRight;
                    }
                }
                
                for(Position p : mobs){
                    if(p.x == x && p.y == y) img = mob;
                }
                
                if (img == null) continue;
                
                gr.drawImage(img, x * scaled_size, y * scaled_size, scaled_size, scaled_size ,null);
            }
        }
    }
    
}
