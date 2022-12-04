package votes_app.servlets;
import kasper.classwork.votes_app.dto.Singer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SingerServet", urlPatterns = "/singer")
public class SingerServet extends HttpServlet {

    private Singer singers = new Singer();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.write("<p>" + "Выберите лучшего исполнителя" + "</p>");

        for (String s : singers.getSinger().keySet()) {
            writer.write("<p>" + s + "</p>");
        }



    }
}