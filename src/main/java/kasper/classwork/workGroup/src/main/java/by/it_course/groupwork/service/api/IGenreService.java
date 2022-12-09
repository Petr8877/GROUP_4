package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    boolean checkName(String name);
    List<GenreDTO> get();

}
