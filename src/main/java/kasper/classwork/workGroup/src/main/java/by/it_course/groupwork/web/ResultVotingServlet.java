package by.it_course.groupwork.web;


import by.it_course.groupwork.dao.service.GenresServiceSingleton;
import by.it_course.groupwork.dao.service.StatisticServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

@WebServlet(name = "ResultVotingServlet", urlPatterns = "/result")
public class ResultVotingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<p>" + "Результаты голосования за лучших Исполнителей: " + "</p>");

        Map<String, Integer> resultSingers = StatisticServiceSingleton.getInstance().getResultSingers();
        print(resultSingers, writer);


        writer.write("<p>" + "Результаты голосования за лучший жанр: " + "</p>");

        Map<String, Integer> resultGenres = StatisticServiceSingleton.getInstance().getResultGenres();
        print(resultGenres, writer);

        writer.write("<p>" + "Результаты голосования за User: " + "</p>");
        Map<String, LocalDateTime> resultUserInfo = StatisticServiceSingleton.getInstance().getUserInfo();
        print(resultUserInfo, writer);
    }

    private <K, V> void print(Map<K, V> map, PrintWriter writer) {
        if (map != null) {
            for (Map.Entry<K, V> param : map.entrySet()) {
                writer.write("<p>" + param.getKey() + " - " + param.getValue() + "</p>");
            }
        }
    }
}
