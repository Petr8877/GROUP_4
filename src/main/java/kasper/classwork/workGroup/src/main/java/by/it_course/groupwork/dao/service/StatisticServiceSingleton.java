package by.it_course.groupwork.dao.service;


import by.it_course.groupwork.service.StatisticsService;
import by.it_course.groupwork.service.api.IStatisticsService;

public class StatisticServiceSingleton {
    private volatile static StatisticsService instance;
    private StatisticServiceSingleton(){

    }

    public static IStatisticsService getInstance() {
        if(instance == null){
            synchronized (StatisticServiceSingleton.class){
                if(instance == null){
                    instance = new StatisticsService(VoteServiceSingleton.getInstance(),
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
