import java.io.Serializable;

public class ScoreRecord implements Comparable<ScoreRecord>, Serializable {
    private String name;
    private int score;

    public ScoreRecord(String record) {
        String[] split = record.split(":");
        name = split[0];
        score = Integer.parseInt(split[1].replaceAll("\\s", ""));
    }


    public int compareTo(ScoreRecord o) {
        if(this.score < o.score) {
            return 1;
        } else if(this.score == o.score) {
            return 0;
        } else {
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return name + ": " + score;
    }
}
