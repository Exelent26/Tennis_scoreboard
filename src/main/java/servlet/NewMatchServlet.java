package servlet;

import dto.OngoingMatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;
import java.io.IOException;
import java.util.UUID;

import static service.OngoingMatchesService.createNewMatch;

@WebServlet("/new-match")

public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");
        // сначала проверяем данные полученные с фронта, если все ок
        // TODO: проверяем есть ли такие игроки в бд
        // начинаем новый матч, потом его помещаем в хэшмапу UUID матча + собственно сам матч
        // редирект на страницу матча

        try {

            UUID newMatchID = createNewMatch(playerOne, playerTwo);
            //TODO: поменять создание нового матча на создание
            // временного дто обьект класса матч это уже модель для сохранения в бд, тут нам нужен временный обьект
            // после работы с этим обьектом мы уже и будем его писать в бдшку.
            resp.sendRedirect(req.getContextPath() + "/match?id=" + newMatchID);
        } catch (RuntimeException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }




    }
}
