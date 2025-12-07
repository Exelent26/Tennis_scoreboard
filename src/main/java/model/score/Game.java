package model.score;

import lombok.Getter;
import util.Winner;

import java.util.Optional;

import static model.score.Point.*;

public class Game {


    @Getter
    private Point[] points ;
    public Game(){
        this.points = new Point[]{
                LOVE,
                LOVE
        };
    }

    public Point getPlayerOneGameScore(){
        return points[0];
    }

    public Point getPlayerTwoGameScore(){
        return points[1];
    }


    public Optional<Winner> pointWinner(Winner winner) {
        int winnerIndex = winner == Winner.PLAYER_ONE ? 0 : 1;
        int loserIndex = winner == Winner.PLAYER_ONE ? 1 : 0;

        return calculateGame(winnerIndex, loserIndex);
        }

    private Optional<Winner> calculateGame(int winnerIndex, int loserIndex) {

        Point winnerPoint = points[winnerIndex];

        switch (winnerPoint) {
            case LOVE -> points[winnerIndex] = FIFTEEN;
            case FIFTEEN -> points[winnerIndex] = THIRTY;
            case THIRTY -> points[winnerIndex] = FORTY;
            case FORTY -> {
                if (points[loserIndex] == FORTY) {
                    points[winnerIndex] = ADVANTAGE;
                }else if(points[loserIndex] == ADVANTAGE){
                    points[loserIndex] = FORTY;
                }
                else {
                    return Optional.of(winnerIndex == 0 ? Winner.PLAYER_ONE : Winner.PLAYER_TWO);
                }
                }
                case ADVANTAGE -> {
                    return Optional.of(winnerIndex == 0 ? Winner.PLAYER_ONE : Winner.PLAYER_TWO);
                }
            }

        return Optional.empty();
    }

}



