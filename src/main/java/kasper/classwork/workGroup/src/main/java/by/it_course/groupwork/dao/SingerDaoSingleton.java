package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.SingerDao;


public class SingerDaoSingleton {
    private volatile static SingerDao instance;

    public static SingerDao getInstance(){
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
