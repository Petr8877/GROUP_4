package groupwork.dao;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenreDao implements IGenreDao {

    private List<GenreDTO> genres = new ArrayList<>();

    {
        genres.add(new GenreDTO("pop", 1));
        genres.add(new GenreDTO("rock", 2));
        genres.add(new GenreDTO("alternative", 3));
        genres.add(new GenreDTO("folk", 4));
        genres.add(new GenreDTO("bluez", 5));
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
            if (genre.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        for (int i = 0; i < genres.size(); i++) {
            GenreDTO genre = genres.get(i);
            if (genre.getId() == id) {
                genres.remove(genre);
                break;
            }
        }
    }

    @Override
    public void create(GenreDTO genreDTO) {
        int maxID = genres.stream()
                .map(GenreDTO::getId)
                .max(Comparator.comparingInt(s -> s))
                .get();
        genreDTO.setId(maxID+1);
        genres.add(genreDTO);
    }

    @Override
    public void update(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        String name = genreDTO.getName();
        for (GenreDTO genre : genres) {
            if (genre.getId() == id) {
                genre.setName(name);
                break;
            }
        }

    }
}
