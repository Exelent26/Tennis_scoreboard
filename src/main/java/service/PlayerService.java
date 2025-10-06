package service;

import lombok.Getter;
import model.Player;

import java.util.UUID;

public class PlayerService {
    private PlayerService() {}

    @Getter
    private static final PlayerService instance = new PlayerService();

    public Player createPlayer(String name) {
        return new Player(UUID.randomUUID(), name);
    }
}
