package scores;

/**
 *
 * @author RDDZXA
 */
public class HighScore {
    
    private final String name;
    private final int score;
    private final String difficulty;
    private final int time;

    public HighScore(String name, String diff, int score, int time) {
        this.name = name;
        this.difficulty = diff;
        this.score = score;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return "HighScore{" + "name: " + name + ", difficulty: " + difficulty + ", score: " + score + ", time spent in game: " + time + "}";
    }
    

}
