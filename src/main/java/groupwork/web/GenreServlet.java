package groupwork.web;

import groupwork.service.fabrics.GenresServiceSingleton;
import groupwork.dto.GenreDTO;
import groupwork.service.api.IGenreService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenreServlet", urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {
    private final IGenreService genreService;
    private final String GENRE_INSERT_PARAM_NAME = "name_insert";
    private final String ID_DELETE_PARAM_NAME = "id_delete";
    private final String ID_UPDATE_PARAM_NAME = "id_update";
    private final String NAME_UPDATE_PARAM_NAME = "name_update";

    public GenreServlet() {
        this.genreService = GenresServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<GenreDTO> genreDTOS = genreService.getAll();

        PrintWriter writer = resp.getWriter();

        genreDTOS.forEach(genreDTO -> writer.write("<p>" + genreDTO + "</p>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String genre = req.getParameter(GENRE_INSERT_PARAM_NAME);
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(genre);
        PrintWriter writer = resp.getWriter();
        if (genreDTO != null) {
            boolean insert = genreService.insert(genreDTO);
            writer.write(String.valueOf(insert));
        } else writer.write("object is not created");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] ids = parameterMap.get(ID_UPDATE_PARAM_NAME);
        int id = Integer.parseInt(ids[0]);
        String[] names = parameterMap.get(NAME_UPDATE_PARAM_NAME);
        String name = names[0];
        // todo validation
        GenreDTO genreDTO = new GenreDTO(name, id);

        PrintWriter writer = resp.getWriter();
        if (genreDTO != null) {
            GenreDTO update = genreService.update(genreDTO);
            writer.write(update.getId() + " " + update.getName());
        } else {
            writer.write("something went wrong");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_genre = req.getParameter(ID_DELETE_PARAM_NAME);
        int id = Integer.parseInt(id_genre);
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(id);
        PrintWriter writer = resp.getWriter();
        if (genreDTO != null) {
            boolean delete = genreService.delete(genreDTO);
            writer.write(String.valueOf(delete));
        } else writer.write("object is not deleted");
    }
}
