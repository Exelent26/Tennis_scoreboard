package service;

import dao.MatchDao;
import dto.OngoingMatch;
import exception.ValidationException;
import model.Player;
import util.PlayerValidator;
import util.Winner;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID,OngoingMatch> matches = new ConcurrentHashMap<>();

    public static Optional<OngoingMatch> getMatch(UUID id) {
        return Optional.ofNullable(matches.get(id));
    }

    public static void removeMatch(UUID id) {
        matches.remove(id);
    }

    public static UUID createNewMatch(String nameOfPlayerOne, String nameOfPlayerTwo) {

        PlayerService playerService = PlayerService.getInstance();
        if(
        !PlayerValidator.isPlayerNameValid(nameOfPlayerOne) ||
        !PlayerValidator.isPlayerNameValid(nameOfPlayerTwo)) {
            throw new ValidationException("One of the players names not valid");
        }
        if ((nameOfPlayerOne.trim()).equalsIgnoreCase(nameOfPlayerTwo.trim())) {
            throw new ValidationException("Players names can't be the same");
        }

        Player playerOne;
        Player playerTwo;

        playerOne = playerService.createPlayer(nameOfPlayerOne);
        playerTwo = playerService.createPlayer(nameOfPlayerTwo);

        OngoingMatch ongoingMatch = new OngoingMatch(playerOne, playerTwo);

        UUID ongoingMatchId = ongoingMatch.getId();

        addOngoingMatch(ongoingMatch, ongoingMatchId);


        return ongoingMatchId;

    }

    private static void addOngoingMatch(OngoingMatch match, UUID ongoingMatchId) {
        matches.put(ongoingMatchId, match);
    }

    public void registerWinner(UUID id, Winner winner) {

        Optional<OngoingMatch> match = getMatch(id);
        if(match.isPresent()) {
            OngoingMatch ongoingMatch = match.get();
            Optional<Winner> winnerOfPlay = ongoingMatch.getScore().pointWonBy(winner);
        }
    }

    public void saveEndedMatches(UUID id) {
        Optional<OngoingMatch> match = getMatch(id);
        if(match.isPresent()) {
            OngoingMatch ongoingMatch = match.get();
            if(ongoingMatch.getScore().isFinished()){
                MatchDao matchDao = MatchDao.getINSTANCE();
                matchDao.
            }
        }
    }

}