package groupwork.dao.api;

import groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreDao {

    void add(String name);

    void delete(int id);

    void update(long id, String name);

    List<GenreDTO> getList();

    boolean isContain(int id);
}
