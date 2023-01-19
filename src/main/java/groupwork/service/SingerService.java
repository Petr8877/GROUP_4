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
        return dao.getSingerList();
    }

    @Override
    public void delete(int id) {
        if(dao.isContain(id)){
            dao.delete(id);
        }else {
            throw new IllegalArgumentException("Нет исполнителя для удаления с таким id");
        }
    }

    @Override
    public void create(String name) {
        if (name != null && !name.isBlank()) {
            dao.create(name);
        } else {
            throw new IllegalArgumentException("Не введен исполнитель");
        }
    }

    @Override
    public void update(int id, SingerDTO singerDTO) {
        String singer = singerDTO.getName();
        if (singer == null || singer.isBlank()) {
            throw new IllegalArgumentException("Не введено новое имя исполнителя");
        }

        if(dao.isContain(id)){
            dao.update(id, singerDTO);
        } else {
            throw new IllegalArgumentException("Нет исполнителя для обновления с таким id");
        }
    }
}
