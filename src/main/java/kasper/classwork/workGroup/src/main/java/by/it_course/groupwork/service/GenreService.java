package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.GenreDao;
import by.it_course.groupwork.dao2.api.IGenreDao;
import by.it_course.groupwork.service.api.IGenreService;


import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao genre;

    public GenreService(IGenreDao instance) {
        genre = instance;
    }

    @Override
    public void check(String[] str) throws Exception {
        for (String s : str) {
            if (!genre.getGenreList().contains(s)) {
                throw new Exception("Please, select a genre from the list");
            }
        }
    }

    @Override
    public List<String> get() {
        return genre.getGenreList();
    }
}
