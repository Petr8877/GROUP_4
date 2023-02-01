package groupwork.service.api;

import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;
import groupwork.entity.GenreEntity;

import java.util.List;

public interface IGenreService {

    boolean check(long number);

    List<GenreDTO> get();

    void delete(GenreDTO genreDTO);

    void create(GenreDTO genreDTO);

    void update(long id, GenreDTO genreDTO);
    GenreDTO get(long id);

}
