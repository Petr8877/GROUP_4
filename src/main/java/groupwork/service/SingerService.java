package groupwork.service;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.fabrics.SingerDaoSingleton;
import groupwork.dto.SingerDTO;
import groupwork.service.api.ISingerService;

import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao dao;

    public SingerService(ISingerDao dao) {
        this.dao = dao;
    }
    @Override
    public boolean isContain(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("Введите номер исполнителя");
        }
        return this.dao.isContain(id);
    }
    @Override
    public List<SingerDTO> getAll()  {
        return dao.getAll();
    }
    @Override
    public boolean insert(SingerDTO singerDTO) {
        boolean insert = dao.insert(singerDTO);
        return insert;
    }
    @Override
    public SingerDTO update(SingerDTO singerDTO) {
        SingerDTO update = dao.update(singerDTO);
        return update;
    }
    @Override
    public boolean delete(SingerDTO singerDTO) {
        boolean delete = dao.delete(singerDTO);
        return delete;
    }
}
