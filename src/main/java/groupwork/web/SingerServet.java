package groupwork.web;

import groupwork.service.fabrics.SingersServiceSingleton;
import groupwork.dto.SingerDTO;
import groupwork.service.api.ISingerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SingerServet", urlPatterns = "/singer")
public class SingerServet extends HttpServlet {
    private final ISingerService service;
    private final String SINGER_ADD_PARAM_NAME = "singer_insert";
    private final String ID_UPDATE_SINGER_PARAM_NAME = "id_update_singer";
    private final String NAME_UPDATE_SINGER_PARAM_NAME = "name_update_singer";

    public SingerServet() {
        this.service = SingersServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<SingerDTO> singerDTOS = service.getAll();

        PrintWriter writer = resp.getWriter();

        singerDTOS.forEach(singerDTO -> writer.write("<p>" + singerDTO + "</p>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String singer = req.getParameter(SINGER_ADD_PARAM_NAME);
        SingerDTO singerDTO = new SingerDTO();
        singerDTO.setName(singer);
        PrintWriter writer = resp.getWriter();
        if (singerDTO != null) {
            boolean insert = service.insert(singerDTO);
            writer.write(String.valueOf(insert));
        } else writer.write("object is not created");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] ids = parameterMap.get(ID_UPDATE_SINGER_PARAM_NAME);
        int id = Integer.parseInt(ids[0]);
        String[] names = parameterMap.get(NAME_UPDATE_SINGER_PARAM_NAME);
        String name = names[0];
        // todo validation
        SingerDTO singerDTO = new SingerDTO(name, id);

        PrintWriter writer = resp.getWriter();
        if (singerDTO != null) {
            SingerDTO update = service.update(singerDTO);
            writer.write(update.getName() + " " + update.getId());
        } else {
            writer.write("something went wrong");
        }
    }
}