package groupwork.dao;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<GenreDTO> getAll() {
        return genres;
    }

    @Override
    public boolean isContain(Integer id) {
        Optional<GenreDTO> first = genres.stream().filter(s -> s.getId() == id).findFirst();

        return first.isPresent();

    }

    @Override
    public boolean insert(GenreDTO genreDTO) {
        int maxId = genres.stream().mapToInt(GenreDTO::getId).max().getAsInt();
        genreDTO.setId(++maxId);
        genres.add(genreDTO);
        return isContain(genreDTO.getId());
    }

    @Override
    public GenreDTO update(GenreDTO genreDTO) {
        GenreDTO genreDTO1 = genres.stream()
                .filter(s -> s.getId() == genreDTO.getId())
                .findFirst()
                .get();
        genreDTO1.setName(genreDTO.getName());
        return genreDTO1;
    }

    @Override
    public boolean delete(GenreDTO genreDTO) {
        GenreDTO genreDTO1 = genres.stream()
                .filter(s -> s.getId() == genreDTO.getId())
                .findFirst()
                .get();
        boolean remove = genres.remove(genreDTO1);
        return remove;
    }
}
