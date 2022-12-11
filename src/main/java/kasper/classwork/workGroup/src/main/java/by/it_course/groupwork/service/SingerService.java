package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.ISingerDao;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.service.api.ISingerService;

import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao dao;

    public SingerService(ISingerDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean checkNumber(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Введите номер исполнителя");
        }
        return this.dao.isContain(number);

    }

    @Override
    public List<SingerDTO> get() {
        return dao.getSingerList();
    }
}
