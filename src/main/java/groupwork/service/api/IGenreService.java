package groupwork.service.api;

import groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    List<GenreDTO> get();

}
