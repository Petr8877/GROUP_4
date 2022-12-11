package by.it_course.groupwork.web;

import by.it_course.groupwork.dao.service.VoteServiceSingleton;
import by.it_course.groupwork.dto.VoiceDTO;
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

    public UserAnswerServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            String SINGER_PARAM_NAME = "singer";
            int[] singers = Arrays
                    .stream(parameterMap.get(SINGER_PARAM_NAME))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int singer = (singers == null) ? null : singers[0];
            if (singers == null || singers.length > 1) {
                throw new IllegalArgumentException("Choose one singer");
            }

            String GENRE_PARAM_NAME = "genre";
            int[] genres = Arrays
                    .stream(parameterMap.get(GENRE_PARAM_NAME))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (genres == null) {
                throw new IllegalArgumentException("Choose genres");
            }

            String ABOUT_USER_PARAM_NAME = "about_user";
            String[] aboutUsers = parameterMap.get(ABOUT_USER_PARAM_NAME);

            String aboutUser = (aboutUsers == null) ? null : aboutUsers[0];
            VoiceDTO voiceDTO = new VoiceDTO(singer, genres, aboutUser);

            service.save(voiceDTO);
            resp.sendRedirect("http://localhost:8080/main1/result");
        } catch (Exception e) {
            writer.write(e.getMessage());
        }
    }
}