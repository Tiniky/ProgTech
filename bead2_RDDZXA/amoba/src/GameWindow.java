
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author RDDZXA
 * 
 * Az osztály egy játék ablakot reprezentál.
 */
public class GameWindow extends Window{
    /**
     * A pálya mérete.
     */
    private int size;
    /**
     * A pályának megfelelő szimulációs mátrix.
     */
    private Game game;
    /**
     * A pálya állapotjelzője.
     */
    private JLabel label;
    /**
     * A pályát létrehozó MenuWindow.
     */
    private MenuWindow menu;
    /**
     * A gombok, amikre rákattintottak a játékosok.
     */
    private ArrayList<JButton> capturedByX, capturedByO;
    /**
     * A koordinátiái a gomboknak, amikre rákattintottak a játékosok.
     */
    private ArrayList<Coordinate<Integer,Integer>> capturedByXcoord, capturedByOcoord;
    
    /**
     * Létrehoz egy játék ablakot.
     * Elindítja a játékot. Mindig az X kezd.
     * @param size  méret(6x6, 10x10, 14x14)
     * @param menu  az őt létrehozó MenuWindow
     */
    public GameWindow(int size, MenuWindow menu){
        this.size = size;
        this.menu = menu;
        menu.getWindowList().add(this);
        game = new Game(size);
        capturedByX = new ArrayList<>();
        capturedByO = new ArrayList<>();
        capturedByXcoord = new ArrayList<>();
        capturedByOcoord = new ArrayList<>();
        
        label = new JLabel();
        textUpToDate();
        
        setSize(1000, 750);
        
        JPanel info = new JPanel();
        JButton newGame = new JButton();
        newGame.setText("NEW GAME");
        newGame.addActionListener( e -> {
            startNewGame();
        });
        
        info.add(label);
        info.add(newGame);
        
        JPanel gaming = new JPanel();
        gaming.setLayout(new GridLayout(size, size));
        
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addMark(gaming, i, j);
            }
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(info, BorderLayout.NORTH);
        getContentPane().add(gaming, BorderLayout.CENTER);
        
        JMenuBar menuBAR = new JMenuBar();
        setJMenuBar(menuBAR);
        JMenu base = new JMenu("MENU");
        menuBAR.add(base);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        base.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
        JMenuItem size6MenuItem = new JMenuItem("6 x 6");
        base.add(size6MenuItem);
        size6MenuItem.addActionListener(e ->{
            this.size = 6;
            startNewGame();
        });
        JMenuItem size10MenuItem = new JMenuItem("10 x 10");
        base.add(size10MenuItem);
        size10MenuItem.addActionListener(e ->{
            this.size = 10;
            startNewGame();
        });
        JMenuItem size14MenuItem = new JMenuItem("14 x 14");
        base.add(size14MenuItem);
        size14MenuItem.addActionListener(e ->{
            this.size = 14;
            startNewGame();
        });
        
        setLocationRelativeTo(null);
    }
    /**
     * Megjelöli a mezőt, amire a játékos kattintott.
     * Sorra meghívja a többi metódust.
     * @param panel  ahol a gomb mátrix van
     * @param i  mező x koordinátája
     * @param j  mező y koordinátája
     */
    private void addMark(JPanel panel, final int i, final int j) {
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player now = game.step(i, j);
                button.setText(now.name());
                textUpToDate();

                Coordinate<Integer, Integer> coordZ = new Coordinate(i, j);
                if(now == Player.X){
                    capturedByX.add(button);
                    capturedByXcoord.add(coordZ);
                } else{
                    capturedByO.add(button);
                    capturedByOcoord.add(coordZ);
                }
                
                int ctr = game.shouldIPrank(i, j, size);
                if(ctr == 1){
                    if(now == Player.X){
                        turnRandomButton(capturedByX, capturedByXcoord);
                    } else{
                        turnRandomButton(capturedByO, capturedByOcoord);
                    }
                } else if(ctr == 2){
                    if(now == Player.X){
                        turnRandomButton(capturedByX, capturedByXcoord);
                        turnRandomButton(capturedByX, capturedByXcoord);
                    } else{
                        turnRandomButton(capturedByO, capturedByOcoord);
                        turnRandomButton(capturedByO, capturedByOcoord);
                    }
                }
                
                checkEnd(i, j);

            }
        });
        
        panel.add(button);
    }
    
    /**
     * Visszafordít egy random gombot, amire már előzőleg az egyik játékos rákattintott.
     * @param btns  a játékos által megjelölt gombok
     * @param cord  a játékos által megjelölt gombok koordinátái
     */
    private void turnRandomButton(ArrayList<JButton> btns, ArrayList<Coordinate<Integer,Integer>> cord){
        Random rand = new Random();
        int random = rand.nextInt(btns.size()-1);
        
        btns.get(random).setText("");
        int x = cord.get(random).getX();
        int y = cord.get(random).getY();
        game.prank(x, y);
        
        btns.remove(random);
        cord.remove(random);
    }
    
    /**
     * Megnézi, hogy véget ért-e már a játék.
     * Ha elfogytak az üres mezők, meghívja az announceTie()-t.
     * Ha az egyik játékosnak kigyűl az 5 összeköthető mező, akkor meghívja announceWinner()-t.
     * @param i  mező x koordinátája
     * @param j  mező y koordinátája
     */
    private void checkEnd(final int i, final int j){
        Player now;
        if(game.getCurrentPlayer() == Player.X)
            now = Player.O;
        else
            now = Player.X;
        
        if(game.getEmpty() == 0){
            announceTie();
        } else{
            boolean winner = game.gameIsWon(i, j, size);
                if(winner){
                    announceWinner(now);
                }
        }
        
    }
    
    /**
     * Kihírdeti a döntetlent.
     */
    private void announceTie(){
        JOptionPane.showMessageDialog(this, "GAME OVER! It's a TIE o.o");
        startNewGame();
    }
    
    /**
     * Kihírdeti a győztest.
     * @param winner  győztes
     */
    private void announceWinner(Player winner){
        JOptionPane.showMessageDialog(this, "GAME OVER! The Winner is: " + winner.name());
        startNewGame();
    }
    
    /**
     * Új játékot indít új játékablakban. Az előzőtől megszabadul.
     */
    private void startNewGame(){
        GameWindow newWin = new GameWindow(size, menu);
        newWin.setVisible(true);
        
        this.dispose();
        menu.getWindowList().remove(this);
    }
    /**
     * Frissíti a szöveget, ami mutatja, hogy ki következik.
     */
    private void textUpToDate(){
        label.setText("Next up is: " + game.getCurrentPlayer().name());
    }
}
