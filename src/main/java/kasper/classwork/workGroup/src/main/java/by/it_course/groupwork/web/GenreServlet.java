package by.it_course.groupwork.web;

import by.it_course.groupwork.dao.service.GenresServiceSingleton;
import by.it_course.groupwork.dto.GenreDTO;
import by.it_course.groupwork.service.GenreService;
import by.it_course.groupwork.service.api.IGenreService;


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
    private final IGenreService genreService;

    public GenreServlet() {
        this.genreService = GenresServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<GenreDTO> genreDTOS = genreService.get();

        PrintWriter writer = resp.getWriter();

        genreDTOS.forEach(genreDTO -> writer.write("<p>" + genreDTO.getName() + "</p>"));
    }
}
