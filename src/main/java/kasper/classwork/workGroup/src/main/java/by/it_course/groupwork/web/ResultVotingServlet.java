package by.it_course.groupwork.web;


import by.it_course.groupwork.dao.service.StatisticServiceSingleton;
import by.it_course.groupwork.dto.AllStatisticDTO;
import by.it_course.groupwork.service.api.IStatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResultVotingServlet", urlPatterns = "/result")
public class ResultVotingServlet extends HttpServlet {
    private final IStatisticsService statisticsService;

    public ResultVotingServlet(){
        this.statisticsService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        AllStatisticDTO allSort = statisticsService.getAllSort();

        writer.write("<p>" + allSort + "</p>");


        /*Map<SingerDTO, Integer> singerDTOIntegerMap = statisticsService.getMapSingers();
        writer.write("<p> Nomination: singers </p>");
            for (Map.Entry<SingerDTO, Integer> param : singerDTOIntegerMap.entrySet()) {
                writer.write("<p>" + param.getKey().getName() + " - " + param.getValue() + "</p>");
            }


        Map<GenreDTO, Integer> mapGenres = statisticsService.getMapGenres();
        writer.write("<p> Nomination: genres </p>");
        for (Map.Entry<GenreDTO, Integer> param : mapGenres.entrySet()) {
            writer.write("<p>" + param.getKey().getName() + " - " + param.getValue() + "</p>");
        }

        Map<String, LocalDateTime> userInfo = statisticsService.getUserInfo();
        writer.write("<p> Information about users </p>");
        for (Map.Entry<String, LocalDateTime> param : userInfo.entrySet()) {
            writer.write("<p>" + param.getKey() + " - " + param.getValue() + "</p>");
        }*/

    }

}
