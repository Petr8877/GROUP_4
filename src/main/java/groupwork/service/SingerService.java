package groupwork.service;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;
import groupwork.service.api.ISingerService;

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
        return dao.getList();
    }
}
