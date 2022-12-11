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

    public ResultVotingServlet() {
        this.statisticsService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        AllStatisticDTO allSort = statisticsService.getAllSort();

        writer.write("<p>" + allSort + "</p>");
    }
}
