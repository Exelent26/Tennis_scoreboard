import model.score.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Winner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    Set set;

    @BeforeEach
    void setUp() {
        set = new Set();
    }

    @Test
    void shouldWinSetSixZeroWithoutTieBreak() {

        for (int i = 0; i < 5; i++) {
            winOneGameWithoutEndingSet(set, Winner.PLAYER_ONE);
        }

        makeStateBeforeWinGame(set, Winner.PLAYER_ONE);
        assertWin(set, Winner.PLAYER_ONE);
        assertEquals(6, set.getGamesPlayerOne());
        assertEquals(0, set.getGamesPlayerTwo());
    }

    @Test
    public void shouldWinSetWithTieBreak(){
        for (int i = 0; i < 5; i++) {
            winOneGameWithoutEndingSet(set, Winner.PLAYER_ONE);
            winOneGameWithoutEndingSet(set, Winner.PLAYER_TWO);
        }
        winOneGameWithoutEndingSet(set, Winner.PLAYER_ONE);
        winOneGameWithoutEndingSet(set, Winner.PLAYER_TWO);
        assertEquals(6, set.getGamesPlayerOne());
        assertEquals(6, set.getGamesPlayerTwo());

        assertTrue(set.isTieBreakActive());

        for (int i = 0; i < 6; i++) {
            assertTrue(set.pointWonBy(Winner.PLAYER_ONE).isEmpty(),"TieBreak not end yet");
        }
        assertWin(set, Winner.PLAYER_ONE);
    }

    @Test
    void shouldWinSetSevenFive(){
        for (int i = 0; i < 5; i++) {
            winOneGameWithoutEndingSet(set, Winner.PLAYER_ONE);
            winOneGameWithoutEndingSet(set, Winner.PLAYER_TWO);
        }
        winOneGameWithoutEndingSet(set, Winner.PLAYER_TWO);
        assertEquals(5, set.getGamesPlayerOne());
        assertEquals(6, set.getGamesPlayerTwo());

        makeStateBeforeWinGame(set, Winner.PLAYER_TWO);
        assertWin(set, Winner.PLAYER_TWO);
    }


    private void assertWin(Set set, Winner winner) {
        Optional<Winner> result = set.pointWonBy(winner);
        assertTrue(result.isPresent(), "Here will be a winner");
        assertEquals(winner, result.get());
    }

    private void makeStateBeforeWinGame(Set set, Winner winner) {
        for (int i = 0; i < 3; i++) {
            assertTrue(set.pointWonBy(winner).isEmpty(), "Set not end yet, need last game");
        }
    }

    private void winOneGameWithoutEndingSet(Set set, Winner winner) {
        for (int i = 0; i < 4; i++) {
            Optional<Winner> result = set.pointWonBy(winner);
            assertTrue(result.isEmpty(), "Set not end in this game");
        }
    }
}

