package by.it_course.groupwork.web;

import by.it_course.groupwork.dao.service.GenresServiceSingleton;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenreServlet", urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.write("<p>"+ "Выберите жанр:" + "</p>");
        List<String > genres = GenresServiceSingleton.getInstance().get();

        if(genres !=null) {
            genres.forEach(singer ->writer.write("<p>" + singer + "</p>"));
        }


    }
}
