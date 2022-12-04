package votes_app.servlets;

import kasper.classwork.votes_app.dto.AnswersStorageSingleton;
import kasper.classwork.votes_app.dto.ShortAboutUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ResultVotingServlet", urlPatterns = "/result")
public class ResultVotingServlet extends HttpServlet {
    private AnswersStorageSingleton singleton = AnswersStorageSingleton.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.write("<p>" + "Результаты голосования за лучшего исполнителя: " + "</p>");

        for (Map.Entry<String, Integer> entry : singleton.getSortSingers().entrySet()) {
            writer.write("<p>" + entry.getKey() + " -> " + entry.getValue() + "</p>");
        }

        writer.write("<p>" + "Результаты голосования за лучший жанр " + "</p>");

        for (Map.Entry<String, Integer> entry : singleton.getSortGenres().entrySet()) {
            writer.write("<p>" + entry.getKey() + " -> " + entry.getValue() + "</p>");
        }

        writer.write("<p>" + "О пользователе: " + "</p>");

        for (ShortAboutUser shortAboutUser : singleton.getSortUserInfo()) {
                writer.write("<p>" + shortAboutUser + "</p>");
        }

    }
}
