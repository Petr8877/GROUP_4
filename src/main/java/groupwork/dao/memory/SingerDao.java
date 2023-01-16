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
    public void add(String name) {
        if (singers.isEmpty()) {
            singers.put(1, new SingerDTO(name, 1));
        } else {
            Set<Integer> set = singers.keySet();
            int key = Collections.max(set) + 1;
            singers.put(key, new SingerDTO(name, key));
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < singers.size(); i++) {
            if (singers.get(i).getId() == id) {
                singers.remove(i);
            }
        }
    }

    @Override
    public void update(long id, String name) {
        for (int i = 0; i < singers.size(); i++) {
            if (singers.get(i).getId() == id) {
                singers.remove(i);
                singers.put(i, new SingerDTO(name, id));
            }
        }
    }

    @Override
    public List<SingerDTO> getList() {
        return new ArrayList<>(singers.values());
    }


    @Override
    public boolean isContain(int id) {
        for (SingerDTO singer : singers.values()) {
            if (singer.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
