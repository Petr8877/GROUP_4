package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.ArrayList;
import java.util.Comparator;
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
    public List<SingerDTO> getSingerList() {
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

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        for (int i = 0; i < singers.size(); i++) {
            SingerDTO singer = singers.get(i);
            if (singer.getId() == id) {
                singers.remove(singer);
                break;
            }
        }
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String singer = singerDTO.getName();
        int maxID = singers.stream()
                .map(SingerDTO::getId)
                .max(Comparator.comparingInt(s -> s))
                .get();
        SingerDTO singerNew = new SingerDTO(singer, maxID+1);
        singers.add(singerNew);
    }

    @Override
    public void update(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        String singer = singerDTO.getName();

        for (SingerDTO singers : singers) {
            if (singers.getId() == id) {
                singers.setName(singer);
                break;
            }
        }
    }
}
