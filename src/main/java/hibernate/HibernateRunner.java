package hibernate;

import lombok.Getter;
import model.Match;
import model.Player;
import model.score.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateRunner {

        @Getter
        private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory(){

            try{
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Player.class);
                configuration.addAnnotatedClass(Match.class);
                return configuration.buildSessionFactory();
            }catch (Exception e){
                throw new RuntimeException("Ошибка инициализации Hibernate", e);

            }
        }

}
