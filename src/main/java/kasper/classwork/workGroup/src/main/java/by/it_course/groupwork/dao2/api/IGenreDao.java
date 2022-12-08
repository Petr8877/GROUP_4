package by.it_course.groupwork.dao2.api;

import java.util.List;

public interface IGenreDao {

    List<String> getGenreList();

    boolean isContain(String str);
}
