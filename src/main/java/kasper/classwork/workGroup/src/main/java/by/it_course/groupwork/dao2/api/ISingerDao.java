package by.it_course.groupwork.dao2.api;

import java.util.List;

public interface ISingerDao {

    List<String> getSingerList();

    boolean isContain(String str);
}
