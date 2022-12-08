package by.it_course.groupwork.service.api;

import java.util.List;

public interface ISingerService {

    /**
     * проветяет на соответствие переданого исполнителя с нашим списком
     * @param str - получаем  из сервлета строку(исполнителя)
     */
    void check(String str) throws Exception;

    /**
     *
     * @return возвращает всех исполнителей из нашего списка(тип List<String>)
     */
    List<String> get();
}
