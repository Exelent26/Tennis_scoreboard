
import dao.PlayerDao;
import model.Player;

import java.util.Optional;

public class main {
    //чтобы чекать всякую юхню
    public static void main(String[] args) {
        PlayerDao dao = PlayerDao.getINSTANCE();
        Optional<Player> player = dao.saveOrGetExisting("Dmitry");
        System.out.println("Saved player: " + player);
    }
}

