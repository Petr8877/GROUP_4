package by.it_course.groupwork.dao.service;


import by.it_course.groupwork.dao.SingerDaoSingleton;
import by.it_course.groupwork.service.SingerService;

public class SingersServiceSingleton {
    private volatile static SingerService instance;


    public static SingerService getInstance() {
        if(instance == null){
            synchronized (SingersServiceSingleton.class){
                if(instance == null){
                    instance = new SingerService(SingerDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
