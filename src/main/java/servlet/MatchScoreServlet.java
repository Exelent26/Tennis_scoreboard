package servlet;

import dto.OngoingMatch;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;
import util.DataValidator;
import util.Winner;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match")
public class MatchScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();

        try {
            UUID matchId = DataValidator.validateAndParseMatchId(id);
            Optional<OngoingMatch> match = ongoingMatchesService.getMatch(matchId);
            if (match.isPresent()) {
                OngoingMatch ongoingMatch = match.get();
                if (ongoingMatch.isFinished()) {
                    ongoingMatchesService.removeMatch(ongoingMatch.getId());
                }
                request.setAttribute("match", ongoingMatch);
                request.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("error", "Match not found");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(request, response);
                return;
            }
        } catch (ValidationException e) {
            request.setAttribute("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(request, response);
            return;

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String winnerParam = request.getParameter("winner");
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();
        Winner winner = null;
        try {
            UUID matchId = DataValidator.validateAndParseMatchId(id);
            Optional<OngoingMatch> match = ongoingMatchesService.getMatch(matchId);
            if (match.isPresent()) {
                request.setAttribute("match", match.get());
                winner = DataValidator.parseAndValidateWinnerParameter(winnerParam);
                ongoingMatchesService.registerWinner(matchId, winner);
                response.sendRedirect("/match?id=" + matchId);
                return;
            } else {
                request.setAttribute("error", "Match not found");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(request, response);
                return;
            }
        } catch (ValidationException e) {
            request.setAttribute("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(request, response);
            return;

        }

    }
}


