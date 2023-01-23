package groupwork.service.api;

import groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    List<GenreDTO> get();

    void delete(int id);

    void create(String name);

    void update(int id, GenreDTO genreDTO);
    String get(Integer id);

}
