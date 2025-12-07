import model.MatchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Winner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class MatchScoreTest {
    private MatchScore matchScore;

    @BeforeEach
    public void setUp() {
        matchScore = new MatchScore();
    }

    @Test
    public void testMatchWinByPlayerOne(){
        winSet(matchScore,Winner.PLAYER_ONE);
        prepareStateForEndOfSet(matchScore,Winner.PLAYER_ONE);
        assertWin(matchScore,Winner.PLAYER_ONE);
        assertEquals(matchScore.getSetsScorePlayerOne(), 2);
    }

    @Test

    public void matchShouldEndAndHaveWinner(){
        winSet(matchScore,Winner.PLAYER_ONE);
        winSet(matchScore,Winner.PLAYER_ONE);

        assertTrue(matchScore.isFinished());
        assertEquals(Winner.PLAYER_ONE, matchScore.getWinnerOfMatch());

    }

    @Test
    public void shouldEndAndIfTakePointNotChangeScore(){
        winSet(matchScore,Winner.PLAYER_ONE);
        winSet(matchScore,Winner.PLAYER_ONE);
        assertTrue(matchScore.isFinished());

        winSet(matchScore,Winner.PLAYER_ONE);
        winSet(matchScore,Winner.PLAYER_ONE);
        winSet(matchScore,Winner.PLAYER_ONE);
        assertEquals(matchScore.getSetsScorePlayerOne(), 2);

    }



    private void assertWin(MatchScore matchScore, Winner winner){
        Optional<Winner> result = matchScore.pointWonBy(winner);
        assertTrue(result.isPresent(), "Here will be a winner");
        assertEquals(winner, result.get());
    }


    private void prepareStateForEndOfSet(MatchScore matchScore, Winner winner){
        for(int i = 0; i < 5 ; i++){
            winGame(matchScore,winner);
        }
        for (int i =0; i < 3; i++) {
            matchScore.pointWonBy(winner);
        }
    }

    private void winSet(MatchScore matchScore, Winner winner) {
        for (int i = 0; i < 6; i++) {
            winGame(matchScore, winner);
        }
    }

    private void winGame(MatchScore matchScore, Winner winner) {
        for (int i = 0; i < 4; i++) {
            matchScore.pointWonBy(winner);
        }
    }
}
