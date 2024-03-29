package groupwork.dao.memory;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class SingerDao implements ISingerDao {
    private Map<Integer, SingerDTO> singers = new ConcurrentHashMap<>();

    {
        singers.put(1, new SingerDTO("Sting", 1));
        singers.put(2, new SingerDTO("Scorpions", 2));
        singers.put(3, new SingerDTO("Madonna", 3));
        singers.put(4, new SingerDTO("GreenDay", 4));
    }

    @Override
    public synchronized List<SingerDTO> getSingerList() {
        return new ArrayList<>(singers.values());
    }


    @Override
    public synchronized boolean isContain(int id) {
        return singers.containsKey(id);
    }

    @Override
    public synchronized void delete(int id) {
        singers.remove(id);
    }

    @Override
    public synchronized void create(String name) {
        if (checkDuplicate(name)) {
            int id = singers.keySet().stream()
                    .max(Comparator.comparing(Integer::intValue))
                    .get() + 1;
            singers.put(id, new SingerDTO(name, id));
        } else {
            throw new IllegalArgumentException("Такой исполнитель уже существует");
        }
    }

    @Override
    public synchronized void update(int id, SingerDTO singerDTO) {
        String name = singerDTO.getName();
        if (checkDuplicate(name)) {
            singers.put(id, singerDTO);
        } else {
            throw new IllegalArgumentException("Такой исполнитель уже существует");
        }
    }

    @Override
    public String get(Integer id) {
        return singers.get(id).getName();
    }

    private synchronized boolean checkDuplicate(String name) {
        return singers.values().stream()
                .map(singerDTO -> singerDTO.getName())
                .noneMatch(s -> s.equals(name));
    }
}
