package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.IGenreDao;
import by.it_course.groupwork.dto.GenreDTO;
import by.it_course.groupwork.service.api.IGenreService;


import java.util.List;
import java.util.Objects;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean check(int number) {
        if (number == 0){
            throw new IllegalArgumentException("Введите номер жанра");
        }
        return this.dao.isContain(number);
    }

    @Override
    public boolean checkName(String name) {
        List<GenreDTO> list = dao.getGenreList();
        for (GenreDTO genreDTO : list) {
            if(Objects.equals(name, genreDTO.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GenreDTO> get() {
        return dao.getGenreList();
    }
}
