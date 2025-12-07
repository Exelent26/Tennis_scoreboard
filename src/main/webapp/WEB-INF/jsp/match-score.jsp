<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--@elvariable id="match" type="dto.OngoingMatch"--%>
<%--@elvariable id="error" type="java.lang.String"--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../css/style.css">

    <script src="../../js/app.js"></script>
</head>

<body>

<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="../../images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="../../index.html">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>

    <c:if test="${not empty error}">
        <div class="container">
            <h1>Current match</h1>
            <p class="error">${error}</p>
        </div>
    </c:if>

    <c:if test="${not empty match}">

        <div class="container">
            <h1>Current match</h1>

            <div class="current-match-image"></div>
            <c:if test="${match.score.finished}">
                <h1 class = winner-title> Winner: ${match.winnerOfMatch}</h1>
            </c:if>
            <section class="score">
                <table class="table">
                    <thead class="result">
                    <tr>
                        <th class="table-text">Player</th>
                        <th class="table-text">Sets</th>
                        <th class="table-text">Games</th>
                        <c:choose>
                            <c:when test="${match.score.currentSet.tieBreakActive}">
                                <td class="table-text">TieBreakPoints</td>
                            </c:when>
                            <c:otherwise>
                                <th class="table-text">Points</th>
                            </c:otherwise>
                        </c:choose>

                    </tr>
                    </thead>
                    <tbody>
                    <tr class="player1">
                        <td class="table-text">
                                ${match.playerOne.name}
                        </td>
                        <td class="table-text">${match.score.setsScorePlayerOne}</td>
                        <td class="table-text">${match.score.currentSet.gamesPlayerOne}</td>
                        <c:choose>
                            <c:when test="${match.score.currentSet.tieBreakActive}">
                                <td class="table-text">
                                        ${match.score.currentSet.tieBreakScorePlayerOne}
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-text">
                                        ${match.score.currentSet.game.playerOneGameScore.displayValue}
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td class="table-text">
                            <c:if test="${not match.score.finished}">
                                <form method="POST" action="${pageContext.request.contextPath}/match">
                                    <input type="hidden" name="id" value="${match.id}">
                                    <button type="submit"
                                            name="winner"
                                            value="playerOneWin"
                                            class="score-btn">
                                        Score
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    <tr class="player2">
                        <td class="table-text">
                                ${match.playerTwo.name}
                        </td>
                        <td class="table-text">${match.score.setsScorePlayerTwo}</td>
                        <td class="table-text">${match.score.currentSet.gamesPlayerTwo}</td>
                        <c:choose>
                            <c:when test="${match.score.currentSet.tieBreakActive}">
                                <td class="table-text">
                                        ${match.score.currentSet.tieBreakScorePlayerTwo}
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-text">
                                        ${match.score.currentSet.game.playerTwoGameScore.displayValue}
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td class="table-text">
                            <c:if test="${not match.score.finished}">
                                <form method="POST" action="${pageContext.request.contextPath}/match">
                                    <input type="hidden" name="id" value="${match.id}">
                                    <button type="submit"
                                            name="winner"
                                            value="playerTwoWin"
                                            class="score-btn">
                                        Score
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </section>
        </div>

    </c:if>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
