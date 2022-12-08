package by.it_course.groupwork.dao2;

import by.it_course.groupwork.dao2.api.IGenreDao;


import java.util.ArrayList;
import java.util.List;


public class GenreDao implements IGenreDao {

    private List<String> genres = new ArrayList<>();

    {
        genres.add("pop");
        genres.add("rock");
        genres.add("alternative");
        genres.add("folk");
        genres.add("bluez");
        genres.add("pop-rock");
        genres.add("jazz");
        genres.add("classic");
        genres.add("electro");
        genres.add("cantry");
    }

    @Override
    public List<String> getGenreList() {
        return genres;
    }

    @Override
    public boolean isContain(String str) {
        return genres.contains(str);
    }
}
