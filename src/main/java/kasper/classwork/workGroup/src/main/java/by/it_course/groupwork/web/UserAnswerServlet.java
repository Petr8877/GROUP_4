package by.it_course.groupwork.web;

import by.it_course.groupwork.dao.service.VoteServiceSingleton;
import by.it_course.groupwork.service.VoteService;
import by.it_course.groupwork.service.api.IVotesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserAnswerServlet", urlPatterns = "/answer")
public class UserAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        String singer = req.getParameter("singer");
        String[] genres = req.getParameterValues("genre");
        String infoAboutUser = req.getParameter("about_user");
        try {
            VoteServiceSingleton.getInstance().check(singer, genres, infoAboutUser);
            VoteServiceSingleton.getInstance().save(singer, genres, infoAboutUser);
            resp.sendRedirect("http://localhost:8080/workGroup/result");
        } catch (Exception e) {
            writer.write(e.getMessage());
        }
    }
}