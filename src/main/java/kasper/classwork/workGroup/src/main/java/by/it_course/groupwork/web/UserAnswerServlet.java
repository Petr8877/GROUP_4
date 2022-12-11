package by.it_course.groupwork.web;

import by.it_course.groupwork.dao.service.StatisticServiceSingleton;
import by.it_course.groupwork.dao.service.VoteServiceSingleton;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.dto.SingleStatisticDTO;
import by.it_course.groupwork.dto.VoiceDTO;
import by.it_course.groupwork.service.api.IStatisticsService;
import by.it_course.groupwork.service.api.IVotesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "UserAnswerServlet", urlPatterns = "/answer")
public class UserAnswerServlet extends HttpServlet {
    private final IVotesService service;
    private final IStatisticsService statisticsService;
    private final String SINGER_PARAM_NAME = "singer";
    private final String GENRE_PARAM_NAME = "genre";
    private final String ABOUT_USER_PARAM_NAME = "about_user";
    public UserAnswerServlet(){
        this.service = VoteServiceSingleton.getInstance();
        this.statisticsService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();
        int[] singers = Arrays
                        .stream(parameterMap.get(SINGER_PARAM_NAME))
                        .mapToInt(s -> Integer.parseInt(s))
                        .toArray();

        int singer = (singers == null) ? null : singers[0];
        if (singers==null || singers.length>1){
            throw new IllegalArgumentException("Choose one singer");
        }

        int [] genres = Arrays
                .stream(parameterMap.get(GENRE_PARAM_NAME))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        if (genres==null ){
            throw new IllegalArgumentException("Choose genres");
        }



        String [] aboutUsers = parameterMap.get(ABOUT_USER_PARAM_NAME);

        String aboutUser = (aboutUsers == null) ? null : aboutUsers[0];
        VoiceDTO voiceDTO = new VoiceDTO(singer,genres,aboutUser);

        service.save(voiceDTO);
//Тестовый прогон SingleStatisticDTO
        PrintWriter writer = resp.getWriter();

        writer.write("<p>" + "Текущий результат голосования" + "</p>");

        writer.write("<p>" + "За исполнителя: " + "</p>");

        SingleStatisticDTO<SingerDTO, Integer> singerAll = statisticsService.getMapSingers();
        writer.write("<p>" + singerAll + "</p>");




//        PrintWriter writer = resp.getWriter();

//        String singer = req.getParameter("singer");
//        String[] genres = req.getParameterValues("genre");
//        String infoAboutUser = req.getParameter("about_user");
//        try {
////            VoteServiceSingleton.getInstance().check(singer, genres, infoAboutUser);
////            VoteServiceSingleton.getInstance().save(singer, genres, infoAboutUser);
////            resp.sendRedirect("http://localhost:8080/workGroup/result");
//        } catch (Exception e) {
//            writer.write(e.getMessage());
//        }
    }
}