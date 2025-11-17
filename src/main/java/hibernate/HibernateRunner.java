package hibernate;

import lombok.Getter;
import model.Player;
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

                return configuration.buildSessionFactory();
            }catch (Exception e){
                throw new RuntimeException("Ошибка инициализации Hibernate", e);

            }
        }

}
