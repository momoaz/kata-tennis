package com.azizi.mohamed.kata.tennis;

public class PlayerScore {
    private Player player;
    private GameScore gameScore;
    private int setScore;
    private int tieBreakScore;

    public PlayerScore(Player player) {
        this.player = player;
        this.gameScore = GameScore.ZERO;
        this.setScore = 0;
        this.tieBreakScore = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public int getSetScore() {
        return setScore;
    }

    public void setSetScore(int setScore) {
        this.setScore = setScore;
    }

    public void incrementSetScore() {
        this.setScore++;
    }

    public int getTieBreakScore() {
        return tieBreakScore;
    }

    public void incrementTieBreakScore() {
        this.tieBreakScore++;
    }

    public String scoreToString(boolean withTieBreak) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.player.getName() + " : ");
        sb.append("Game Score " + this.gameScore.getScore());
        sb.append(" | Set Score " + this.setScore);
        if(withTieBreak) {
            sb.append(" | Tie Break Score " + this.tieBreakScore);
        }
        return sb.toString();
    }
}
