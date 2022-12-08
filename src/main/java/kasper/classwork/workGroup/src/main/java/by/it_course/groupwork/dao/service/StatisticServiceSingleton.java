package by.it_course.groupwork.dao.service;


import by.it_course.groupwork.service.StatisticsService;

public class StatisticServiceSingleton {
    private volatile static StatisticsService instance;

    public static StatisticsService getInstance() {
        if(instance == null){
            synchronized (StatisticServiceSingleton.class){
                if(instance == null){
                    instance = new StatisticsService( );
                }
            }
        }
        return instance;
    }
}
