package by.it_course.groupwork.web;
import by.it_course.groupwork.dao.service.SingersServiceSingleton;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.service.api.ISingerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SingerServet", urlPatterns = "/singer")
public class SingerServet extends HttpServlet {
    private final ISingerService singerService;

    public SingerServet() {
        this.singerService = SingersServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<SingerDTO> singerDTOS = singerService.get();

        PrintWriter writer = resp.getWriter();

        singerDTOS
                .forEach(singerDTO -> writer.write("<p>"+singerDTO.getName()+"</p>"));
     }
}