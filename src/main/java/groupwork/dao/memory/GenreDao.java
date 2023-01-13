package groupwork.dao.memory;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreDao implements IGenreDao {

    private List<GenreDTO> genres = new ArrayList<>();

    {
        genres.add(new GenreDTO("pop", 1));
        genres.add(new GenreDTO("rock", 2));
        genres.add(new GenreDTO("alternative", 3));
        genres.add(new GenreDTO("folk", 4));
        genres.add(new GenreDTO("bluez", 5));
        genres.add(new GenreDTO("pop-rock", 6));
        genres.add(new GenreDTO("jazz", 7));
        genres.add(new GenreDTO("classic", 8));
        genres.add(new GenreDTO("electro", 9));
        genres.add(new GenreDTO("cantry", 10));
    }

    @Override
    public void add(String name) {
    int tmp = genres.size();
    genres.add(new GenreDTO(name, tmp));
    }

    @Override
    public void delete(String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (Objects.equals(name, genres.get(i).getName())){
                genres.remove(i);
            }
        }
//        List<GenreDTO> tmplist = genres;
//        for (GenreDTO genreDTO : tmplist) {
//            if (genreDTO.getName() == name){
//                genres.remove(genreDTO.getId());
//            }
//        }
    }

    @Override
    public void update(long id, String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).getId() == id){
                genres.remove(i);
                genres.add(i, new GenreDTO(name, id));
            }
        }
    }

    @Override
    public List<GenreDTO> getList() {
        return genres;
    }

    @Override
    public boolean isContain(int id) {
        for (GenreDTO genre : genres) {
            if (genre.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
