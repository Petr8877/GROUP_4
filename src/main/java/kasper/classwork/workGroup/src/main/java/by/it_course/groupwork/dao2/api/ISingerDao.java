package by.it_course.groupwork.dao2.api;

import by.it_course.groupwork.dto.SingerDTO;
import java.util.List;

public interface ISingerDao {

    List<SingerDTO> getSingerList();

    boolean isContain(String str);
}
