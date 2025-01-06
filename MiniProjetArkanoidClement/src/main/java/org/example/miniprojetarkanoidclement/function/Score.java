package org.example.miniprojetarkanoidclement.function;

public class Score {
    private int score;
    private long cooldownPoint;
    public Score() {
        this.score = 0;
        this.cooldownPoint = System.currentTimeMillis();
    }

    public void incrementScoreOnBreak() {
        score += 10;
    }

    public int getScore() {
        return score;
    }

    public void updateScoreWithTimeBonus() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - cooldownPoint;
        if (elapsedTime >= 5000) {
            score++;
            cooldownPoint = currentTime;
        }
    }
}
