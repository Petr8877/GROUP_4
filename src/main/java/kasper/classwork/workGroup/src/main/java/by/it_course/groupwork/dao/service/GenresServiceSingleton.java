package by.it_course.groupwork.dao.service;



import by.it_course.groupwork.dao.GenreDaoSingleton;
import by.it_course.groupwork.service.GenreService;
import by.it_course.groupwork.service.api.IGenreService;

public class GenresServiceSingleton {
    private volatile static GenreService instance;

    private GenresServiceSingleton(){}

    public static IGenreService getInstance() {
        if(instance == null){
            synchronized (GenresServiceSingleton.class){
                if(instance == null){
                    instance = new GenreService(GenreDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
