package dao;

import hibernate.HibernateRunner;
import lombok.Getter;
import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerDao {

    private PlayerDao(){}

    @Getter
    private static final PlayerDao INSTANCE = new PlayerDao();

    public Optional<Player> saveOrGetExisting(String playerName) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Player existing = session.createQuery(
                            "from Player where name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResult();

            if (existing != null) {
                return Optional.of(existing);
            }
            session.beginTransaction();
            Player player = Player.builder()
                    .name(playerName)
                    .build();
            session.persist(player);
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

    public Optional<Player> getPlayerByName(String playerName){
        try(Session session = HibernateRunner.getSessionFactory().openSession()) {

            Player player = session.createQuery(
                            "from Player p where p.name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResult();

            return Optional.ofNullable(player);

        }
    }

    public Optional<Player> getPlayerById(Long id) {

        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();
            Player player = session.get(Player.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

    public List<Player> getAllPlayers(){
        List<Player> players;
        try(Session session = HibernateRunner.getSessionFactory().openSession()){

            players = new ArrayList<>(session.createQuery("from Player", Player.class).getResultList());
            if (!players.isEmpty()){
                return players;
            }
            return players;
        }
    }

}
