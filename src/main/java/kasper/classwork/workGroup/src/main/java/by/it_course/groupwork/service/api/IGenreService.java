package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    List<GenreDTO> get();

}
