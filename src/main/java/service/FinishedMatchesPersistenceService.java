package service;

import dao.MatchDao;
import lombok.Getter;

import java.util.List;

public class FinishedMatchesPersistenceService {

    @Getter
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private MatchDao matchDao = MatchDao.getINSTANCE();

    public List getAllFinishedMatches() {

        List allMatches = matchDao.findAll();

        return allMatches;
    }

}
