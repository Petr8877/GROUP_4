package votes_app.servlets;

import kasper.classwork.votes_app.dto.ShortAboutUser;
//import votes_app.dto.UserAnswer;
import kasper.classwork.votes_app.dto.AnswersStorageSingleton;
import kasper.classwork.votes_app.workers.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserAnswerServlet", urlPatterns = "/answer")
public class UserAnswerServlet extends HttpServlet {
    private DataValidator dataValidator = new DataValidator();
    private AnswersStorageSingleton answersStorageSingleton = AnswersStorageSingleton.getInstance();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        String singer = req.getParameter("singer");
        String[]genres = req.getParameterValues("genre");
        String infoAboutUser = req.getParameter("about_user");

        if(dataValidator.checkCorrectChoiceGenres(genres)
                && singer != null
                && dataValidator.checkCorrectUserInfo(infoAboutUser)){

            ShortAboutUser shortAboutUser = new ShortAboutUser(infoAboutUser);

            answersStorageSingleton.addVoiceSinger(singer);
            answersStorageSingleton.addVoiceGenre(genres);
            answersStorageSingleton.addUserInfo(shortAboutUser);

            writer.write("<p>" + "Ответ сохранен" + "</p>");

        } else {
            writer.write("<p>" + "Некорректный ответ. Повторите попытку." + "</p>");
        }


    }
}
