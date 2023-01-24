package groupwork.web.controllers;

import groupwork.dto.*;
import groupwork.service.api.IStatisticsService;
import groupwork.service.api.IVotesService;
import groupwork.service.fabrics.StatisticServiceSingleton;
import groupwork.service.fabrics.VoteServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CheckVotingServlet", urlPatterns = "/check")
public class CheckVotingServlet extends HttpServlet {
    private final String ID_PARAM_NAME = "id";
    private final String KEY_PARAM_NAME = "key";
    private final IVotesService service;

    public CheckVotingServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<Long, Long> map = service.getIdAndKey();

        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] idRaw = parameterMap.get(ID_PARAM_NAME);

        long id = 0;
        long key = 0;
        if (!(idRaw == null || idRaw.length > 1)) {
            id = Integer.parseInt(idRaw[0]);
        }

        String[] keyRaw = parameterMap.get(KEY_PARAM_NAME);
        if (!(keyRaw == null || keyRaw.length > 1)) {
            key = Integer.parseInt(keyRaw[0]);
        }

        if (map.containsKey(id) && map.get(id).equals(key)) {
            service.auth(id);
        }
        PrintWriter writer = resp.getWriter();

        writer.write("Ответ сохранен");

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/result");

    }
}
