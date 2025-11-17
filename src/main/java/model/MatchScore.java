package model;

import lombok.AccessLevel;
import lombok.Getter;
import model.score.Set;
import util.Winner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class MatchScore {

    private static final int TARGET_SETS_TO_WIN = 2;
    private List<Set> finishedSets;
    @Getter(AccessLevel.NONE)
    private final int[] setsScore;
    private boolean finished;
    private Winner winnerOfMatch;
    private Set currentSet;

    public MatchScore() {
        finishedSets = new ArrayList<>();
        setsScore = new int[]{0,
                          0};
        finished = false;
        winnerOfMatch = null;
        currentSet = new Set();

    }

    public int[] getSetsScore() {
        return Arrays.copyOf(setsScore, setsScore.length);
    }

    public Optional<Winner> pointWonBy(Winner winner) {
        if(finished) {
            return Optional.of(winnerOfMatch);
        }
        return calculateMatchScore(winner);
    }

    private Optional<Winner> calculateMatchScore( Winner winner) {

        Optional<Winner> winnerOfSet = currentSet.pointWonBy(winner);
        if(winnerOfSet.isEmpty()){
            return Optional.empty();
        }
        int winnerIndex = winnerOfSet.get() == Winner.PLAYER_ONE ? 0 : 1;
        setsScore[winnerIndex]++;
        finishedSets.add(currentSet);
        if (setsScore[winnerIndex] == TARGET_SETS_TO_WIN) {
            finished = true;
            winnerOfMatch = winnerOfSet.get();
            return Optional.of(winnerOfMatch);
        }
        currentSet = new Set();
        return Optional.empty();

    }
}
