package service;

import dao.PlayerDao;
import exception.ValidationException;
import lombok.Getter;
import model.Player;
import util.PlayerValidator;

import java.util.Optional;

public class PlayerService {
    private PlayerService() {}

    @Getter
    private static final PlayerService instance = new PlayerService();

    PlayerDao playerDao = PlayerDao.getINSTANCE();

    public Player createPlayer(String name) {
        if(PlayerValidator.isPlayerNameValid(name)) {

            Optional<Player> player = playerDao.saveOrGetExisting(name);
            if(player.isPresent()) {
                return player.get();
            }
        }
        throw new ValidationException("Player name is invalid " + name);
    }

    public Player getPlayerByName(String name) {
        if(PlayerValidator.isPlayerNameValid(name)) {
            Optional<Player> playerByName = playerDao.getPlayerByName(name);
            if(playerByName.isPresent()) {
                return playerByName.get();
            }

        }
        throw new ValidationException("Player name is invalid " + name);
    }

    public Player getPlayerById(Long id) {
        if (PlayerValidator.isIdValid(id)) {
            Optional<Player> playerById = playerDao.getPlayerById(id);
            if(playerById.isPresent()) {
                return playerById.get();
            }
        }
        throw new ValidationException("Player id is invalid " + id);
    }
}
