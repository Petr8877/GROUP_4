package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;
import groupwork.entity.Entity;

import java.util.ArrayList;
import java.util.List;


public class SingerDao implements ISingerDao {
    private List<SingerDTO> singers = new ArrayList<>();

    {
        singers.add(new SingerDTO("Sting", 1));
        singers.add(new SingerDTO("Scorpions", 2));
        singers.add(new SingerDTO("Madonna", 3));
        singers.add(new SingerDTO("GreenDay", 4));
    }

    @Override
    public List<SingerDTO> getAll() {
        return singers;
    }

    @Override
    public boolean isContain(Integer id) {
        boolean contains = singers.contains(id);
        return contains;
    }

    @Override
    public boolean insert(SingerDTO singerDTO) {
        singers.add(singerDTO);
        return false;
    }

    @Override
    public SingerDTO update(SingerDTO singerDTO) {
        return singerDTO;
    }

    @Override
    public boolean delete(SingerDTO singerDTO) {
        return false;
    }

}
