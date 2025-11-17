package model.score;

import util.Winner;

import java.util.Optional;

import static model.score.Point.LOVE;

public class GamOldUselessVersion {

    private Point playerOnePoints = LOVE;
    private Point playerTwoPoints = LOVE;


    public Optional<Winner> pointWonBy(Winner winner) {
        return winner == Winner.PLAYER_ONE
                ? handlePlayerOneWinsPoint()
                : handlePlayerTwoWinsPoint();
    }

    private Optional<Winner> updateWinnerPoints(Point playerOnePoints, Point playerTwoPoints) {

        return switch (playerOnePoints) {
            case LOVE -> {
                playerOnePoints = Point.FIFTEEN;
                yield Optional.empty();
            }
            case FIFTEEN -> {
                playerOnePoints = Point.THIRTY;
                yield Optional.empty();
            }
            case THIRTY -> {
                playerOnePoints = Point.FORTY;
                yield Optional.empty();
            }

            case FORTY -> handleFortyCaseForPlayerOne();
            case ADVANTAGE -> Optional.of(Winner.PLAYER_ONE);
        };

    }

    private Optional<Winner> handlePlayerOneWinsPoint() {
        return switch (playerOnePoints) {
            case LOVE -> {
                playerOnePoints = Point.FIFTEEN;
                yield Optional.empty();
            }
            case FIFTEEN -> {
                playerOnePoints = Point.THIRTY;
                yield Optional.empty();
            }
            case THIRTY -> {
                playerOnePoints = Point.FORTY;
                yield Optional.empty();
            }

            case FORTY -> handleFortyCaseForPlayerOne();
            case ADVANTAGE -> Optional.of(Winner.PLAYER_ONE);
        };
    }

    private Optional<Winner> handlePlayerTwoWinsPoint() {
        return switch (playerTwoPoints) {
            case LOVE -> {
                playerTwoPoints = Point.FIFTEEN;
                yield Optional.empty();
            }
            case FIFTEEN -> {
                playerTwoPoints = Point.THIRTY;
                yield Optional.empty();
            }
            case THIRTY -> {
                playerTwoPoints = Point.FORTY;
                yield Optional.empty();
            }
            case FORTY -> handleFortyCaseForPlayerTwo();
            case ADVANTAGE -> Optional.of(Winner.PLAYER_TWO);

        };
    }

    private Optional<Winner> handleFortyCaseForPlayerOne() {
        if (playerTwoPoints == Point.FORTY) {
            playerOnePoints = Point.ADVANTAGE;
            return Optional.empty();
        } else if (playerTwoPoints != Point.ADVANTAGE && playerTwoPoints != Point.FORTY) {
            return Optional.of(Winner.PLAYER_ONE);
        } else if (playerTwoPoints == Point.ADVANTAGE) {
            playerTwoPoints = Point.FORTY;
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<Winner> handleFortyCaseForPlayerTwo() {
        if (playerOnePoints == Point.FORTY) {
            playerTwoPoints = Point.ADVANTAGE;
            return Optional.empty();
        } else if (playerOnePoints != Point.ADVANTAGE && playerOnePoints != Point.FORTY) {
            return Optional.of(Winner.PLAYER_TWO);
        } else if (playerOnePoints == Point.ADVANTAGE) {
            playerOnePoints = Point.FORTY;
            return Optional.empty();
        }
        return Optional.empty();
    }


}
