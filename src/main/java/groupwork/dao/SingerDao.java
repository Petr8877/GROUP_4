package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class SingerDao implements ISingerDao {
    //private List<SingerDTO> singers = new ArrayList<>();
    private Map<Integer, SingerDTO> singers = new ConcurrentHashMap<>();

    /*{
        singers.add(new SingerDTO("Sting", 1));
        singers.add(new SingerDTO("Scorpions", 2));
        singers.add(new SingerDTO("Madonna", 3));
        singers.add(new SingerDTO("GreenDay", 4));
    }*/

    {
        singers.put(1, new SingerDTO("Sting", 1));
        singers.put(2, new SingerDTO("Scorpions", 2));
        singers.put(3, new SingerDTO("Madonna", 3));
        singers.put(4, new SingerDTO("GreenDay", 4));
    }

    @Override
    public List<SingerDTO> getSingerList() {
        return new ArrayList<>(singers.values());
        //return singers;
    }


    @Override
    public boolean isContain(int id) {
        return singers.containsKey(id);
        /*for (SingerDTO singer : singers) {
            if (singer.getId() == id) {
                return true;
            }
        }
        return false;*/
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        singers.remove(id);

        /*for (int i = 0; i < singers.size(); i++) {
            SingerDTO singer = singers.get(i);
            if (singer.getId() == id) {
                singers.remove(singer);
                break;
            }
        }*/
    }

    @Override
    public void create(SingerDTO singerDTO) {
        int id = getMaxID();
        singerDTO.setId(id);
        singers.put(id,singerDTO);
        /*String singer = singerDTO.getName();
        SingerDTO singerNew = new SingerDTO(singer, getMaxID());
        singers.add(singerNew);*/
    }

    @Override
    public void update(SingerDTO singerDTO) {
        singers.put(singerDTO.getId(), singerDTO);
        //int id = singerDTO.getId();
        //String singer = singerDTO.getName();

        /*for (SingerDTO singers : singers) {
            if (singers.getId() == id) {
                singers.setName(singer);
                break;
            }
        }*/
    }

    private synchronized int getMaxID(){
        int currID = singers.keySet().stream()
                .max(Comparator.comparing(Integer::intValue))
                .get();
        return ++currID;
    }
}
