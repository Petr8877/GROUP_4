package by.it_course.groupwork.service.api;

import java.util.List;

public interface IGenreService {

    /**
     * проветяет на соответствие переданых жанров с нашим списком
     * проверяет услови 3 < массив жанров > 5
     * @param str - получаем  из сервлета массив строк(жанров)
     */
    void check(String[] str) throws Exception;

    /**
     *
     * @return возвращает все жанры из нашего списка(тип List<String>)
     */
    List<String> get();

}
