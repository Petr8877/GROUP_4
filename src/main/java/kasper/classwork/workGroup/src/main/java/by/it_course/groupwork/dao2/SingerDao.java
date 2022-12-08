package by.it_course.groupwork.dao2;



import by.it_course.groupwork.dao2.api.ISingerDao;

import java.util.ArrayList;
import java.util.List;


public class SingerDao implements ISingerDao {
    private List<String> singers = new ArrayList<>();

    {
        singers.add("Sting");
        singers.add("Scorpions");
        singers.add("Madonna");
        singers.add("GreenDay");
    }

    @Override
    public List<String> getSingerList() {
        return singers;
    }

    @Override
    public boolean isContain(String str) {
        return singers.contains(str);
    }
}
