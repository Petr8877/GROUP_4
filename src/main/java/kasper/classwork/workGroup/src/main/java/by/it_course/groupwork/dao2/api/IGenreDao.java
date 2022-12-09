package by.it_course.groupwork.dao2.api;

import by.it_course.groupwork.dto.GenreDTO;
import java.util.List;

public interface IGenreDao {

    List<GenreDTO> getGenreList();

    boolean isContain(String str);
}
