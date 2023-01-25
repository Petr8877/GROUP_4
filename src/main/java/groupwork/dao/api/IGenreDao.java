package groupwork.dao.api;

import groupwork.dto.GenreDTO;
import groupwork.entity.GenreEntity;

import java.util.List;

public interface IGenreDao {
//исправить на entity
    List<GenreEntity> getGenreList();

    boolean isContain(int id);

    void delete(GenreEntity genreEntity);

    void create(GenreEntity genreEntity);

    void update( GenreEntity genreEntity);
    GenreEntity get(Integer id);
}
