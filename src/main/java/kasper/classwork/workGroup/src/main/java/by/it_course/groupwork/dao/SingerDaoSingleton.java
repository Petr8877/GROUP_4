package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.SingerDao;
import by.it_course.groupwork.dao2.api.ISingerDao;

public class SingerDaoSingleton {
    private volatile static SingerDao instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance(){
        if(instance == null){
            synchronized (SingerDaoSingleton.class){
                if(instance==null){
                    instance = new SingerDao();
                }
            }
        }
        return instance;
    }


}
