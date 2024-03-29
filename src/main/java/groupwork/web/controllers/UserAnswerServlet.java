package groupwork.web.controllers;

import groupwork.dto.VoiceDTO;
import groupwork.service.MailService;
import groupwork.service.api.IVotesService;
import groupwork.service.fabrics.MailServiceSingleton;
import groupwork.service.fabrics.VoteServiceSingleton;

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
    private final MailService sendEMailService;
    private final String EMAIL_TO = "mail";
    private final String SINGER_PARAM_NAME = "singer";
    private final String GENRE_PARAM_NAME = "genre";
    private final String ABOUT_USER_PARAM_NAME = "about_user";


    public UserAnswerServlet() {
        this.service = VoteServiceSingleton.getInstance();
        this.sendEMailService = MailServiceSingleton.getInstance();
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

            String[] singers = parameterMap.get(SINGER_PARAM_NAME);

            if (singers == null || singers.length > 1) {
                throw new IllegalArgumentException("Choose one singer");
            }

            int singer = Integer.parseInt(singers[0]);

            String[] genres = parameterMap.get(GENRE_PARAM_NAME);

            if (genres == null) {
                throw new IllegalArgumentException("Choose genres");
            }

            int[] intGenre = Arrays.
                    stream(genres)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            String[] mails = parameterMap.get(EMAIL_TO);

            if (mails == null || mails.length > 1) {
                throw new IllegalArgumentException("Choose one mail");
            }

            String mail = mails[0];

            String[] aboutUsers = parameterMap.get(ABOUT_USER_PARAM_NAME);

            String aboutUser = (aboutUsers == null) ? null : aboutUsers[0];

            VoiceDTO voiceDTO = new VoiceDTO(singer, intGenre, aboutUser,mail);

            service.save(voiceDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/result");

        } catch (RuntimeException e) {
            if (e.getCause() != null) {
                writer.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                writer.write("<p>" + e.getMessage() + "</p>");
            }
        }
    }
}