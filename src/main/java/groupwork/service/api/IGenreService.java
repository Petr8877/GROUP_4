package groupwork.service.api;

import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;
import groupwork.entity.GenreEntity;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    List<GenreDTO> get();

    void delete(GenreDTO genreDTO);

    void create(GenreDTO genreDTO);

    void update(int id, GenreDTO genreDTO);
    GenreDTO get(int id);

}
