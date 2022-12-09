package by.it_course.groupwork.dao2;

import by.it_course.groupwork.dao2.api.ISingerDao;
import by.it_course.groupwork.dto.SingerDTO;
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
    public List<SingerDTO> getSingerList() {
        return singers;
    }

    @Override
    public boolean isContain(int id) {
        for (SingerDTO singer : singers) {
            if(singer.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
