package dto;

import lombok.Getter;
import model.MatchScore;
import model.Player;
import util.Winner;

import java.util.Optional;
import java.util.UUID;

@Getter
public class OngoingMatch {
    private final UUID id;
    private final Player playerOne;
    private final Player playerTwo;
    private final MatchScore score;
    // свой класс

    public OngoingMatch(Player player1, Player player2) {
        this.id = UUID.randomUUID();
        this.playerOne = player1;
        this.playerTwo = player2;
        this.score = new MatchScore();
    }

    public String getWinnerOfMatch() {
        Winner winnerOfMatch = this.score.getWinnerOfMatch();

        return winnerOfMatch == Winner.PLAYER_ONE ? playerOne.getName() : playerTwo.getName();
    }

    public boolean isFinished() {
        return this.score.isFinished();
    }

    public Optional<Player> findWinnerOfAMatch() {
        if (this.isFinished()) {
            Winner winnerOfMatch = score.getWinnerOfMatch();
            return winnerOfMatch == Winner.PLAYER_ONE ? Optional.of(playerOne) : Optional.of(playerTwo);
        }
        return Optional.empty();

    }

}
