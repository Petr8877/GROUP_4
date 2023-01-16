package groupwork.dao.memory;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GenreDao implements IGenreDao {

    private Map<Integer, GenreDTO> genres = new ConcurrentHashMap<>();

    {
        genres.put(1, new GenreDTO("pop", 1));
        genres.put(2, new GenreDTO("rock", 2));
        genres.put(3, new GenreDTO("alternative", 3));
        genres.put(4, new GenreDTO("folk", 4));
        genres.put(5, new GenreDTO("bluez", 5));
        genres.put(6, new GenreDTO("pop-rock", 6));
        genres.put(7, new GenreDTO("jazz", 7));
        genres.put(8, new GenreDTO("classic", 8));
        genres.put(9, new GenreDTO("electro", 9));
        genres.put(10, new GenreDTO("cantry", 10));
    }

    @Override
    public void add(String name) {
        if (genres.isEmpty()) {
            genres.put(1, new GenreDTO(name, 1));
        } else {
            Set<Integer> set = genres.keySet();
            int key = Collections.max(set) + 1;
            genres.put(key, new GenreDTO(name, key));
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).getId() == id) {
                genres.remove(i);
            }
        }
    }

    @Override
    public void update(long id, String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).getId() == id) {
                genres.remove(i);
                genres.put(i, new GenreDTO(name, id));
            }
        }
    }

    @Override
    public List<GenreDTO> getList() {
        return new ArrayList<>(genres.values());
    }

    @Override
    public boolean isContain(int id) {
        for (GenreDTO genre : genres.values()) {
            if (genre.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
