import model.score.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Winner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void shouldWinGameAfterFourPointsWithoutDeuce(){

        winPoints(game, Winner.PLAYER_ONE, 3);
        assertWin(game,Winner.PLAYER_ONE);
    }



    @Test
    public void shouldWinGameAfterAD(){
        winPoints(game, Winner.PLAYER_ONE, 3);
        winPoints(game, Winner.PLAYER_TWO, 3);

        assertTrue(game.pointWinner(Winner.PLAYER_ONE).isEmpty());

        assertWin(game,Winner.PLAYER_ONE);
    }

    @Test
    public void shouldWinGameAfterDeuceAfterAd(){
        winPoints(game, Winner.PLAYER_ONE, 3);
        winPoints(game, Winner.PLAYER_TWO, 3);

        assertTrue(game.pointWinner(Winner.PLAYER_ONE).isEmpty());
        assertTrue(game.pointWinner(Winner.PLAYER_TWO).isEmpty());
        assertTrue(game.pointWinner(Winner.PLAYER_TWO).isEmpty());

        assertWin(game,Winner.PLAYER_TWO);
    }

     private void winPoints(Game game, Winner winner, int times){
        for(int i = 0; i < times; i++){
            assertTrue(game.pointWinner(winner).isEmpty(), "Here cant be win");
        }
     }

     private void assertWin(Game game, Winner winner){
        Optional<Winner> result = game.pointWinner(winner);
        assertTrue(result.isPresent(), "Here will be a winner");
        assertEquals(winner, result.get());
     }
}
