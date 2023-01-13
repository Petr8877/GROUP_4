package groupwork.dao.memory;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SingerDao implements ISingerDao {
    private List<SingerDTO> singers = new ArrayList<>();

    {
        singers.add(new SingerDTO("Sting", 1));
        singers.add(new SingerDTO("Scorpions", 2));
        singers.add(new SingerDTO("Madonna", 3));
        singers.add(new SingerDTO("GreenDay", 4));
    }

    @Override
    public void add(String name) {
    singers.add(new SingerDTO(name, singers.size()));
    }

    @Override
    public void delete(String name) {
        for (int i = 0; i < singers.size(); i++) {
            if (Objects.equals(name, singers.get(i).getName())){
                singers.remove(i);
            }
        }
    }

    @Override
    public void update(long id, String name) {
        for (int i = 0; i < singers.size(); i++) {
            if (singers.get(i).getId() == id){
                singers.remove(i);
                singers.add(i, new SingerDTO(name, id));
            }
        }
    }

    @Override
    public List<SingerDTO> getList() {
        return singers;
    }


    @Override
    public boolean isContain(int id) {
        for (SingerDTO singer : singers) {
            if (singer.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
