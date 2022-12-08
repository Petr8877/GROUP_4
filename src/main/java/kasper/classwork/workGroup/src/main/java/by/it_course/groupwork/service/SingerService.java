package by.it_course.groupwork.service;


import by.it_course.groupwork.dao2.SingerDao;
import by.it_course.groupwork.dao2.api.ISingerDao;
import by.it_course.groupwork.service.api.ISingerService;

import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao singer;

    public SingerService(SingerDao instance) {
        singer = instance;
    }

    @Override
    public void check(String str) throws Exception {

        if (!singer.getSingerList().contains(str)) {
            throw new Exception("Please, select a singer from the list");
        }
    }


    @Override
    public List<String> get() {
        return singer.getSingerList();
    }
}
