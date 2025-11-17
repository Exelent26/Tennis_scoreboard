package dto;

import lombok.Getter;
import model.MatchScore;
import model.Player;

import java.util.UUID;

@Getter
public class OngoingMatch {
    private final UUID id;
    private final Player player1;
    private final Player player2;
    private MatchScore score;
    // свой класс

    public OngoingMatch(Player player1, Player player2) {
        this.id = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.score = new MatchScore();
    }

}
