package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.IGenreDao;
import by.it_course.groupwork.dto.GenreDTO;
import by.it_course.groupwork.service.api.IGenreService;


import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean check(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Введите номер жанра");
        }
        return this.dao.isContain(number);

    }

    @Override
    public List<GenreDTO> get() {
        return dao.getGenreList();
    }
}
