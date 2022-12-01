
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;

/**
 *
 * @author RDDZXA
 * 
 * Az osztály egy ablakot reprezentál.
 */
public class Window extends JFrame{

    /**
     * Beállítja az ablak nevét, méretét és ikonját. Valamint, hogy bezárja a programot kilépésnél.
     */
    public Window() {
        setTitle("Kiszúrós Amőba");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        URL img = Window.class.getResource("logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(img));
    }
}
