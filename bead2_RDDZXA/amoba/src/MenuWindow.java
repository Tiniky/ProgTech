
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author RDDZXA
 * 
 * Az osztály a main menüt reprezentálja.
 */
public class MenuWindow extends Window {
     /**
     * A megnyitott játék ablakok listája.
     */
    private ArrayList<GameWindow> windowZ;
     /**
     * A pálya mérete.
     */
    private JPanel selectSize;
    
    /**
     * Létrehoz egy main menü ablakot, ahol ki lehet választani a pálya méretét a gombok egyikére kattintva.
     */
    public MenuWindow(){
        this.windowZ = new ArrayList<>();
        selectSize = new JPanel();
        
        String sizes[] = {"6 x 6", "10 x 10", "14 x 14"};
        selectSize.setPreferredSize( new Dimension( 600, 250 ) );
        selectSize.setLayout(new GridLayout(1, sizes.length));
        
        for(int i=0; i<sizes.length; i++){
            JButton btn = new JButton();
            btn.setText(sizes[i]);
            switch(i){
                case 0:
                    btn.addActionListener(e ->{
                        GameWindow win = new GameWindow(6, MenuWindow.this);
                        win.setVisible(true);
                        windowZ.add(win);
                    });
                    break;
                case 1:
                    btn.addActionListener(e ->{
                        GameWindow win = new GameWindow(10, MenuWindow.this);
                        win.setVisible(true);
                        windowZ.add(win);
                    });
                    break;
                case 2:
                    btn.addActionListener(e ->{
                        GameWindow win = new GameWindow(14, MenuWindow.this);
                        win.setVisible(true);
                        windowZ.add(win);
                    });
                    break;
            }
            selectSize.add(btn);
        }
        
        getContentPane().add(BorderLayout.CENTER, selectSize);
        
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu base = new JMenu("MENU");
        menu.add(base);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        base.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
        
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Visszaadja a megnyitott ablakok listáját.
     * @return megnyitott ablakok
     */
    public ArrayList<GameWindow> getWindowList(){
        return this.windowZ;
    }
}
