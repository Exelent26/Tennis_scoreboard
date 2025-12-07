package dao;

import exception.DomainException;
import hibernate.HibernateRunner;
import lombok.Getter;
import model.Match;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MatchDao {
    private MatchDao() {}
    @Getter
    private static final MatchDao INSTANCE = new MatchDao();

    //TODO: требуется написать методы добавления всех матчей в список чтобы выводить на страницы матчей
    // создать метод добавления завершенного матча в таблицу в базе данных

    public void save(Match savedMatch) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.persist(savedMatch);
            session.getTransaction().commit();

        }catch (DomainException e){
            throw new DomainException(e.getMessage());

        }
    }

    public Optional<Match> findById(int id) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();
            Match match = session.get(Match.class, id);
            session.getTransaction().commit();
            return Optional.of(match);
        }
    }

    public List<Match> findAll() {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Match> matches = session.createQuery("from Match", Match.class).list();
            session.getTransaction().commit();
            return matches;
        }
    }

}
