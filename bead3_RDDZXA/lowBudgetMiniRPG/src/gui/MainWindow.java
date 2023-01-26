package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import lowbudgetminirpg.Difficulty;
import lowbudgetminirpg.Direction;
import lowbudgetminirpg.Game;
import lowbudgetminirpg.GameID;
import scores.HighScore;
import scores.HighScoresManager;


/**
 *
 * @author RDDZXA
 */
public final class MainWindow extends JFrame{
    private final Game game;
    private final HighScoresManager highScoresManager;
    private Board board;
    
    private final JLabel timeLabel;    
    private int elapsedTime;
    private final Timer timer;
    private  final JLabel collectedCountLabel;
    private boolean isStepping = false;
    
    public static void main(String[] args) throws SQLException {
            MainWindow mainW = new MainWindow();
    }
    
    public MainWindow() throws SQLException{
        game = new Game();
        highScoresManager = new HighScoresManager(10);
        isStepping = true;
        
        setTitle("Mini RPG Adventure");
        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("res/logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuGameLevel = new JMenu("Difficulties");
        createGameLevelMenuItems(menuGameLevel);

        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("Hall of Fame") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showHighScores();
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menuGame.add(menuGameLevel);
        menuGame.add(menuHighScores);
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        timeLabel = new JLabel("Elapsed time: 0");
        labelPanel.add(timeLabel, BorderLayout.NORTH);
        collectedCountLabel = new JLabel("Number of treasure collected: 0");
        labelPanel.add(collectedCountLabel, BorderLayout.SOUTH);
        topPanel.add(labelPanel, BorderLayout.WEST);
        
        add(topPanel, BorderLayout.NORTH);
        
        try { 
            add(board = new Board(game), BorderLayout.SOUTH);
        } catch (IOException ex) {
            System.out.println("F");
        }
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); 
                handleKeyPressed(ke);
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        
        game.loadGame(new GameID(Difficulty.EASY));
        board.refresh();
        
        elapsedTime = 0;
        timer = new Timer(1000, (ActionEvent e) -> {
            elapsedTime++;
            refreshTimerLabel();
            game.enemyStep();
            game.updateEnemyPos();
            game.meetUpCheck();
            board.repaint(); 
        });
        timer.start();
        refreshTimerLabel();
        
        pack();
        setVisible(true);
        
        setFocusable(true);
        setLocationRelativeTo(null);
        isStepping = false;
        
    }
    
    private void refreshTimerLabel(){
        timeLabel.setText("Elapsed time: " + elapsedTime);
    }
    
    private void restartTimer(){
        timer.restart();
        elapsedTime = 0;
        refreshTimerLabel();
    }
    
    private void refreshCollectedCountLabel(){
        collectedCountLabel.setText("Number of treasure collected: " + game.getCollectedNum());
    }
    
    private void handleKeyPressed(KeyEvent ke){
        if (!game.isLevelLoaded()) {
            return;
        }
        if (isStepping) return;
        isStepping = true;
        
        int kk = ke.getKeyCode();
        Direction d = null;
        switch(kk){
            case KeyEvent.VK_LEFT -> d = Direction.LEFT;
            case KeyEvent.VK_RIGHT -> d = Direction.RIGHT;
            case KeyEvent.VK_UP -> d = Direction.UP;
            case KeyEvent.VK_DOWN -> d = Direction.DOWN;
                    
            case KeyEvent.VK_ESCAPE -> {
                game.loadGame(game.getGameID());
                restartTimer();
            }
        }
        board.repaint();
        
        if(d != null && game.stillGoing()){
            game.step(d);
            game.meetUpCheck();
            
            refreshCollectedCountLabel();
            
            if(!game.stillGoing()){
                timer.stop();
                showGGDialog();
            }
        }
        isStepping = false;
    }
    
    public void showGGDialog(){
        JOptionPane.showMessageDialog(MainWindow.this, "Congrats on getting this far! I see the potential in you, Adventurer!",  "GG", JOptionPane.INFORMATION_MESSAGE);
        String name = JOptionPane.showInputDialog("Name:");
            try {
                highScoresManager.putHighScore(name, game.getGameID().getDiff().name(), game.getCollectedNum(), elapsedTime);
            } catch (SQLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void showHighScores() throws SQLException{
        ArrayList<HighScore> scores = highScoresManager.getHighScores();
        HighScoresWindow window = new HighScoresWindow(scores);
    }
    
    private void createGameLevelMenuItems(JMenu menu){
        for (Difficulty d : game.getDifficulties()){
            JMenuItem difficultyMenuItem = new JMenuItem(new AbstractAction(d.name()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.loadGame(new GameID(d));
                    restartTimer();
                    refreshCollectedCountLabel();
                    board.refresh();
                    pack();
                }
            });
            menu.add(difficultyMenuItem);
        }
    }
}
