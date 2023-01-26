package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import scores.HighScore;

/**
 *
 * @author RDDZXA
 */
public class HighScoresWindow extends JFrame {

    public HighScoresWindow(ArrayList<HighScore> scores) {
        this.setTitle("HALL OF FAME");
        
        String colNames[] = {"NAME", "DIFFICULTY", "TREASURE COLLECTED", "TIME SPENT INGAME"};
        
        String[][] data = new String[scores.size()][4];
        for(int i = 0; i < scores.size(); i++){
            data[i][0] = scores.get(i).getName();
            data[i][1] = scores.get(i).getDifficulty();
            data[i][2] = String.valueOf(scores.get(i).getScore());
            data[i][3] = String.valueOf(scores.get(i).getTime());
        }      
                
        JTable table = new JTable(data, colNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        
        setSize(500,300);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
}
