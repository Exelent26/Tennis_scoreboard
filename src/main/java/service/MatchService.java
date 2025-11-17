package service;

import lombok.Getter;
import model.Match;
import model.Player;

public class MatchService {
    private MatchService(){}

    @Getter
    private static final MatchService instance = new MatchService();

}
