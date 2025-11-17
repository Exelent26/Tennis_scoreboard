package model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_one_id")
    private Player playerOneID;

    @ManyToOne
    @JoinColumn(name = "player_two_id")
    private Player playerTwoID;



    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winnerID;

    public Match(Player playerOne, Player playerTwo) {
        this.playerOneID = playerOne;
        this.playerTwoID = playerTwo;
    }
}
