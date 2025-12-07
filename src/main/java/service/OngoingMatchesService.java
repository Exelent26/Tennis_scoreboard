package service;

import dao.MatchDao;
import dto.OngoingMatch;
import exception.ValidationException;
import lombok.Getter;
import mapper.MatchMapper;
import model.Match;
import model.Player;
import util.DataValidator;
import util.Winner;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();
    @Getter
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    public Optional<OngoingMatch> getMatch(UUID id) {
        return Optional.ofNullable(matches.get(id));
    }

    public void removeMatch(UUID id) {
        matches.remove(id);
    }



    public UUID createNewMatch(String nameOfPlayerOne, String nameOfPlayerTwo) {

        PlayerService playerService = PlayerService.getInstance();
        if (
                !DataValidator.isPlayerNameValid(nameOfPlayerOne) ||
                !DataValidator.isPlayerNameValid(nameOfPlayerTwo)) {
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

        this.addOngoingMatch(ongoingMatch, ongoingMatchId);

        return ongoingMatchId;

    }

    private  void addOngoingMatch(OngoingMatch match, UUID ongoingMatchId) {
        matches.put(ongoingMatchId, match);
    }

    public void registerWinner(UUID id, Winner winner) {

        Optional<OngoingMatch> match = this.getMatch(id);
        if (match.isPresent()) {
            OngoingMatch ongoingMatch = match.get();
            Optional<Winner> winnerOfPlay = ongoingMatch.getScore().pointWonBy(winner);
            if (ongoingMatch.isFinished()) {
                saveEndedMatches(ongoingMatch);

            }
        }
    }

    public void saveEndedMatches(OngoingMatch ongoingMatch) {

        if (ongoingMatch.getScore().isFinished()) {
            MatchDao matchDao = MatchDao.getINSTANCE();
            Match endedMatch = MatchMapper.INSTANCE.toEntity(ongoingMatch);
            matchDao.save(endedMatch);
        }
    }
}
