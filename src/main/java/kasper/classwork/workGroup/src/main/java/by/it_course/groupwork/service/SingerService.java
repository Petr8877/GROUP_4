package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.ISingerDao;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.service.api.ISingerService;

import java.util.List;
import java.util.Objects;

public class SingerService implements ISingerService {

    private final ISingerDao dao;

    public SingerService(ISingerDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean checkNumber(int number) {
        if (number == 0){
            throw new IllegalArgumentException("Введите номер исполнителя");
        }
        return this.dao.isContain(number);

    }

    @Override
    public boolean checkName(String name) {
    List<SingerDTO> list = dao.getSingerList();
        for (SingerDTO singerDTO : list) {
            if(Objects.equals(name, singerDTO.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SingerDTO> get() {
        return dao.getSingerList();
    }
}
