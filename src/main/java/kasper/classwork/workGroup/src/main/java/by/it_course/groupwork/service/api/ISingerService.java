package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    boolean checkName(String name);

    List<SingerDTO> get();

}
