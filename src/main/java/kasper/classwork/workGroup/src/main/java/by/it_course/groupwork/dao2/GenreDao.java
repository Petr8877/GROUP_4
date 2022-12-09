package by.it_course.groupwork.dao2;

import by.it_course.groupwork.dao2.api.IGenreDao;
import by.it_course.groupwork.dto.GenreDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreDao implements IGenreDao {

    private List<GenreDTO> genres = new ArrayList<>();

    {
        genres.add(new GenreDTO("pop", 1));
        genres.add(new GenreDTO("rock", 2));
        genres.add(new GenreDTO("alternative", 3));
        genres.add(new GenreDTO("folk", 4));
        genres.add(new GenreDTO("bluez",5));
        genres.add(new GenreDTO("pop-rock", 6));
        genres.add(new GenreDTO("jazz", 7));
        genres.add(new GenreDTO("classic", 8));
        genres.add(new GenreDTO("electro", 9));
        genres.add(new GenreDTO("cantry", 10));
    }

    @Override
    public List<GenreDTO> getGenreList() {
        return genres;
    }

    @Override
    public boolean isContain(int id) {
        for (GenreDTO genre : genres) {
            if(genre.getId() == id){
                return true;
            }
        }
        return false;
    }
}
