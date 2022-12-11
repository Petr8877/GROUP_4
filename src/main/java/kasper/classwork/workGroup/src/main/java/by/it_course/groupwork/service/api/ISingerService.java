package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerDTO> get();

}
