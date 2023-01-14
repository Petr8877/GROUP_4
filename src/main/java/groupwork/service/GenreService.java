package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.service.api.IGenreService;


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
        GenreDTO update = dao.update(genreDTO);
        return update;
    }
    @Override
    public boolean delete(GenreDTO genreDTO) {
        boolean delete = dao.delete(genreDTO);
        return delete;
    }


}
