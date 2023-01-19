package groupwork.helper;

import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IStatisticsService;
import groupwork.service.api.IVotesService;
import groupwork.service.fabrics.*;

public class Provider {

    private static boolean isDB = true;

    public static ISingerService loadSingerService(){
        if(isDB){
            return SingersServiceDBSingleton.getInstance();
        }else {
            return SingersServiceSingleton.getInstance();
        }
    }

    public static IGenreService loadGenreService(){
        if(isDB){
            return GenresServiceDBSingleton.getInstance();
        }else {
            return GenresServiceSingleton.getInstance();
        }
    }

    public static IVotesService loadVoteService(){
        if(isDB){
            return VoteServiceDBSingleton.getInstance();
        }else {
            return VoteServiceSingleton.getInstance();
        }
    }

    public static IStatisticsService loadStatisticService(){
        if(isDB){
            return StatisticsServiceDBSingleton.getInstance();
        } else {
            return StatisticServiceSingleton.getInstance();
        }
    }
}
