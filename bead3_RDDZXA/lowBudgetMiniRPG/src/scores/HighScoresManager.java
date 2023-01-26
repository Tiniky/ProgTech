package scores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 *
 * @author RDDZXA
 */
public class HighScoresManager {
    
    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;
    
    public HighScoresManager(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        connectionProps.put("serverTimezone", "UTC");
        String dbURL = "jdbc:mysql://localhost:3306/highscores?verifyServerCertificate=false&useSSL=true";
        connection = DriverManager.getConnection(dbURL, connectionProps);
        String insertQuery = "INSERT INTO HIGHSCORES (TIMESTAMP, NAME, DIFFICULTY, SCORE, TIME_SPENT_INGAME) VALUES (?, ?, ?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }
    
    public ArrayList<HighScore> getHighScores() throws SQLException{
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            String difficulty = results.getString("DIFFICULTY");
            int score = results.getInt("SCORE");
            int time = results.getInt("TIME_SPENT_INGAME");
            highScores.add(new HighScore(name, difficulty, score, time));
        }
        sortHighScores(highScores);
        return highScores;
    }
    
    public void putHighScore(String name, String difficulty, int score, int timeSpentInGame) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();
        if (highScores.size() < maxScores) {
            insertScore(name, difficulty, score, timeSpentInGame);
        } else {
            int leastScore = highScores.get(highScores.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(name, difficulty, score, timeSpentInGame);
            }
        }
    }

    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    private void insertScore(String name, String difficulty, int score, int timeSpentInGame) throws SQLException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        insertStatement.setTimestamp(1, ts);
        insertStatement.setString(2, name);
        insertStatement.setString(3, difficulty);
        insertStatement.setInt(4, score);
        insertStatement.setInt(5, timeSpentInGame);
        insertStatement.executeUpdate();
    }

    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
}
