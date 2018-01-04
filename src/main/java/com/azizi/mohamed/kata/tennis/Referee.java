package com.azizi.mohamed.kata.tennis;

public class Referee implements Observer {

    private PlayerScore playerOneScore;
    private PlayerScore playerTwoScore;

    private Player gameWinner;
    private Player setWinner;
    private boolean isTieBreak;

    public Referee(Player playerOne, Player playerTwo) {
        this.initGame(playerOne, playerTwo);
    }

    public PlayerScore getPlayerOneScore() {
        return playerOneScore;
    }

    public PlayerScore getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void initGame(Player playerOne, Player playerTwo) {
        this.playerOneScore = new PlayerScore(playerOne);
        this.playerTwoScore = new PlayerScore(playerTwo);
        this.gameWinner = null;
        this.setWinner = null;
        this.isTieBreak = false;
        playerOne.registerObserver(this);
        playerTwo.registerObserver(this);
    }

    public String displayGameScore() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.playerOneScore.scoreToString(this.isTieBreak));
        sb.append("\n");
        sb.append(this.playerTwoScore.scoreToString(this.isTieBreak));

        if(gameWinner != null) {
            sb.append("\n");
            sb.append(gameWinner.getName()).append(" wins the game");
        }

        if(setWinner != null) {
            sb.append("\n");
            sb.append(setWinner.getName()).append(" wins the set");
        }

        return sb.toString();
    }

    private void increasePlayerOneGameScore() {
        increasePlayerScore(this.playerOneScore, this.playerTwoScore);
    }

    private void increasePlayerTwoGameScore() {
        increasePlayerScore(this.playerTwoScore, this.playerOneScore);
    }

    private void increasePlayerScore(PlayerScore winner, PlayerScore looser) {
        this.gameWinner = null;
        this.setWinner = null;
        this.isTieBreak = winner.getSetScore() >= 6 && looser.getSetScore() >= 6;

        if (!this.isTieBreak) {
            switch (winner.getGameScore()) {
                case ZERO:
                    winner.setGameScore(GameScore.FIFTEEN);
                    break;
                case FIFTEEN:
                    winner.setGameScore(GameScore.THIRTY);
                    break;
                case THIRTY:
                    winner.setGameScore(GameScore.FOURTY);
                    break;
                case FOURTY:
                    if (GameScore.FOURTY.equals(looser.getGameScore())) {
                        winner.setGameScore(GameScore.ADVANTAGE);
                    } else if (GameScore.ADVANTAGE.equals(looser.getGameScore())) {
                        winner.setGameScore(GameScore.DEUCE);
                        looser.setGameScore(GameScore.DEUCE);
                    } else {
                        winner.setGameScore(GameScore.ZERO);
                        looser.setGameScore(GameScore.ZERO);
                        winner.incrementSetScore();
                        this.gameWinner = winner.getPlayer();
                        if(this.isSetWinner(winner, looser)) {
                            this.setWinner = winner.getPlayer();
                        }
                    }
                    break;
                case DEUCE:
                    winner.setGameScore(GameScore.ADVANTAGE);
                    looser.setGameScore(GameScore.FOURTY);
                    break;
                case ADVANTAGE:
                    winner.setGameScore(GameScore.ZERO);
                    looser.setGameScore(GameScore.ZERO);
                    winner.incrementSetScore();
                    this.gameWinner = winner.getPlayer();
                    if(this.isSetWinner(winner, looser)) {
                        this.setWinner = winner.getPlayer();
                    }
                    break;
                default:
                    break;
            }
        } else {
            winner.incrementTieBreakScore();
            if(this.isTieBreakWinner(winner, looser)) {
                this.setWinner = winner.getPlayer();
            }
        }
    }

    private boolean isSetWinner(PlayerScore winner, PlayerScore looser) {
        return winner.getSetScore() == 6 && looser.getSetScore() < 5 || winner.getSetScore() == 7 && looser.getSetScore() <= 5;
    }

    private boolean isTieBreakWinner(PlayerScore winner, PlayerScore looser) {
        return winner.getTieBreakScore() >= 7 && winner.getTieBreakScore() >= looser.getTieBreakScore() + 2;
    }

    @Override
    public void onEvent(Observable observable) {
        if(observable instanceof Player) {
            Player player = (Player) observable;
            if(this.playerOneScore.getPlayer().equals(player)) {
                this.increasePlayerOneGameScore();
            } else if(this.playerTwoScore.getPlayer().equals(player)) {
                this.increasePlayerTwoGameScore();
            }
        }
    }
}
