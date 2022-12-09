package by.it_course.groupwork.dao.service;


import by.it_course.groupwork.dao.SingerDaoSingleton;
import by.it_course.groupwork.service.SingerService;
import by.it_course.groupwork.service.api.ISingerService;

public class SingersServiceSingleton {
    private volatile static SingerService instance;

private SingersServiceSingleton(){}

    public static ISingerService getInstance() {
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
