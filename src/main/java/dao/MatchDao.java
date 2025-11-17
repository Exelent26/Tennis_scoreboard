package dao;

import lombok.Getter;

public class MatchDao {
    private MatchDao() {}
    @Getter
    private static final MatchDao INSTANCE = new MatchDao();

    //TODO: требуется написать методы добавления всех матчей в список чтобы выводить на страницы матчей
    // создать метод добавления завершенного матча в таблицу в базе данных


}
