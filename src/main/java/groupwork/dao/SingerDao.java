package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;


public class SingerDao implements ISingerDao {
    private List<SingerDTO> singers = new ArrayList<>();

    {
        singers.add(new SingerDTO("Sting", 1));

        singers.add(new SingerDTO("Scorpions",  2));
        singers.add(new SingerDTO("Madonna", 3));
        singers.add(new SingerDTO("GreenDay", 4));
    }

    @Override
    public List<SingerDTO> getAll() {
        return singers;
    }

    @Override
    public boolean isContain(Integer id) {
        Optional<SingerDTO> first = singers.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        return first.isPresent();
    }
// work
    @Override
    public boolean insert(SingerDTO singerDTO) {
        int maxId = singers.stream()
                .mapToInt(SingerDTO::getId)
                .max()
                .getAsInt();
        singerDTO.setId(++maxId);
        boolean add = singers.add(singerDTO);
        return add;
    }

    @Override
    public SingerDTO update(SingerDTO singerDTO) {
        SingerDTO singerDTO1 = singers.stream()
                .filter(s -> s.getId() == singerDTO.getId())
                .findFirst()
                .get();
        singerDTO1.setName(singerDTO.getName());
        return singerDTO1;
    }

    @Override
    public boolean delete(SingerDTO singerDTO) {
        SingerDTO singerDTO1 = singers.stream()
                .filter(s -> s.getId() == singerDTO.getId())
                .findFirst()
                .get();
        boolean remove = singers.remove(singerDTO1);
        return remove;
    }

}
