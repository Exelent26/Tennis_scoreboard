package model.score;

import lombok.Getter;
import util.Winner;

import java.util.Optional;

public class Set {
    @Getter
    private int[] games;

    @Getter
    private boolean tieBreakActive;


    @Getter
    private int[] tieBreakScore;
    @Getter
    private Game game;

    public int getGamesPlayerOne(){
        return games[0];
    }
    public int getGamesPlayerTwo(){
        return games[1];
    }

    public int getTieBreakScorePlayerOne(){
        return tieBreakScore[0];
    }

    public int getTieBreakScorePlayerTwo(){
        return tieBreakScore[1];
    }

    public Set() {
        this.games = new int[]{
                0,
                0
        };
        this.tieBreakActive = false;
        this.tieBreakScore = new int[]{
                0,
                0
        };
        this.game = new Game();
    }

    public Optional<Winner> pointWonBy(Winner winner) {
        int winnerIndex = winner == Winner.PLAYER_ONE ? 0 : 1;
        int loserIndex = winner == Winner.PLAYER_ONE ? 1 : 0;

        return calculateSets(winnerIndex, loserIndex, winner);
    }

    private Optional<Winner> calculateSets(int winnerIndex, int loserIndex, Winner winner) {
        if(tieBreakActive) {
            Optional<Winner> tieBreakWinner = calculateTieBreak(winnerIndex, loserIndex);
            if(tieBreakWinner.isPresent()) {
                games[winnerIndex]++;
            }
            return tieBreakWinner;

        }else {
            Optional<Winner> gameState = game.pointWinner(winner);
            if (gameState.isEmpty()) {
                return Optional.empty();
            } else {
                games[winnerIndex]++;
                game = new Game();
                if (games[winnerIndex] >= 6 && (games[winnerIndex] - games[loserIndex]) >= 2) {
                    return Optional.of(winnerIndex == 0 ? Winner.PLAYER_ONE : Winner.PLAYER_TWO);
                } else if (games[winnerIndex] == 6 && games[loserIndex] == 6) {
                    tieBreakActive = true;
                }
            }
            return Optional.empty();
        }
    }

    private Optional<Winner> calculateTieBreak(int winnerIndex, int loserIndex) {
        tieBreakScore[winnerIndex]++;
        if (tieBreakScore[winnerIndex] >= 7 && (tieBreakScore[winnerIndex] - tieBreakScore[loserIndex]) >= 2) {
            return Optional.of(winnerIndex == 0 ? Winner.PLAYER_ONE : Winner.PLAYER_TWO);
        }
        return Optional.empty();
    }

}

