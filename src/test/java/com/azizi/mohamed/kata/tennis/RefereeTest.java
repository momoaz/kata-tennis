package com.azizi.mohamed.kata.tennis;

import org.junit.Test;

import static com.azizi.mohamed.kata.tennis.Utils.PLAYER_ONE_NAME;
import static com.azizi.mohamed.kata.tennis.Utils.PLAYER_TWO_NAME;
import static org.junit.Assert.assertEquals;

public class RefereeTest {

    private Player playerOne = new Player(PLAYER_ONE_NAME);
    private Player playerTwo = new Player(PLAYER_TWO_NAME);

    private Referee referee = new Referee(playerOne, playerTwo);

    @Test
    public void should_display_initial_game_scores_to_zero() {
        // given

        // when
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(GameScore.ZERO, referee.getPlayerOneScore().getGameScore());
        assertEquals(GameScore.ZERO, referee.getPlayerTwoScore().getGameScore());
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_15_when_win_point_from_0() {
        // given

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(GameScore.FIFTEEN, referee.getPlayerOneScore().getGameScore());
        assertEquals(PLAYER_ONE_NAME + " : Game Score 15 | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_30_when_win_from_15() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.FIFTEEN);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(GameScore.THIRTY, referee.getPlayerOneScore().getGameScore());
        assertEquals(PLAYER_ONE_NAME + " : Game Score 30 | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_40_when_win_point_from_30() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.THIRTY);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(GameScore.FOURTY, referee.getPlayerOneScore().getGameScore());
        assertEquals(PLAYER_ONE_NAME + " : Game Score 40 | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_winner_and_init_score_to_zero_and_increment_setscore_when_win_point_from_40_and_other_player_under_40() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.FOURTY);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 1\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0\n" + PLAYER_ONE_NAME + " wins the game", scoresToDisplay);
    }

    @Test
    public void should_display_ADV_when_win_point_and_both_players_have_40() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.FOURTY);
        referee.getPlayerTwoScore().setGameScore(GameScore.FOURTY);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score ADV | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 40 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_DEUCE_when_player_loose_advantage() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.ADVANTAGE);
        referee.getPlayerTwoScore().setGameScore(GameScore.FOURTY);

        // when
        playerTwo.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score DEUCE | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score DEUCE | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_ADV_and_40_when_win_point_and_both_players_have_DEUCE() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.DEUCE);
        referee.getPlayerTwoScore().setGameScore(GameScore.DEUCE);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score ADV | Set Score 0\n" + PLAYER_TWO_NAME + " : Game Score 40 | Set Score 0", scoresToDisplay);
    }

    @Test
    public void should_display_winner_and_increment_setscore_and_init_score_to_zero_when_win_point_from_advantage() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.ADVANTAGE);
        referee.getPlayerTwoScore().setGameScore(GameScore.FOURTY);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 1\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0\n" + PLAYER_ONE_NAME + " wins the game", scoresToDisplay);
    }

    @Test
    public void should_display_set_winner_when_score_6_and_other_lower_than_5() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.FOURTY);
        referee.getPlayerOneScore().setSetScore(5);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 6\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 0\n" + PLAYER_ONE_NAME + " wins the game\n" + PLAYER_ONE_NAME + " wins the set", scoresToDisplay);
    }

    @Test
    public void should_display_set_winner_when_score_7_and_other_lower_than_6() {
        // given
        referee.getPlayerOneScore().setGameScore(GameScore.FOURTY);
        referee.getPlayerOneScore().setSetScore(6);
        referee.getPlayerTwoScore().setSetScore(5);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 7\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 5\n" + PLAYER_ONE_NAME + " wins the game\n" + PLAYER_ONE_NAME + " wins the set", scoresToDisplay);
    }

    @Test
    public void should_activate_tie_break_when_set_scores_are_both_to_6() {
        // given
        referee.getPlayerOneScore().setSetScore(6);
        referee.getPlayerTwoScore().setSetScore(6);

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 6 | Tie Break Score 1\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 6 | Tie Break Score 0", scoresToDisplay);
    }

    @Test
    public void should_win_set_on_tie_break_when_7_points_and_difference_greater_than_2() {
        // given
        referee.getPlayerOneScore().setSetScore(6);
        referee.getPlayerTwoScore().setSetScore(6);

        for(int i = 1; i < 7; i++)
            referee.getPlayerOneScore().incrementTieBreakScore();

        // when
        playerOne.winPoint();
        String scoresToDisplay = referee.displayGameScore();

        // then
        assertEquals(PLAYER_ONE_NAME + " : Game Score 0 | Set Score 6 | Tie Break Score 7\n" + PLAYER_TWO_NAME + " : Game Score 0 | Set Score 6 | Tie Break Score 0\n" + PLAYER_ONE_NAME + " wins the set", scoresToDisplay);
    }
}
