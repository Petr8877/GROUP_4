package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.service.api.IGenreService;


import java.sql.SQLException;
import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }
    @Override
    public boolean isContain(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("Введите номер жанра");
        }
        return this.dao.isContain(id);
    }
    @Override
    public List<GenreDTO> getAll() {
        return dao.getAll();
    }
    @Override
    public boolean insert(GenreDTO genreDTO) {
        boolean insert = dao.insert(genreDTO);
        return insert;
    }
    @Override
    public GenreDTO update(GenreDTO genreDTO) {
        GenreDTO update;
        try {
            update = dao.update(genreDTO);
        } catch (RuntimeException e){
            throw new RuntimeException("Error DB with updating of element "+ e.getMessage());
        }

        return update;
    }
    @Override
    public boolean delete(GenreDTO genreDTO) {
        boolean delete = dao.delete(genreDTO);
        return delete;
    }


}
