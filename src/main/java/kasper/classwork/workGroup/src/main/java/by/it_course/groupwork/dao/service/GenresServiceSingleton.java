package by.it_course.groupwork.dao.service;



import by.it_course.groupwork.dao.GenreDaoSingleton;
import by.it_course.groupwork.service.GenreService;

public class GenresServiceSingleton {
    private volatile static GenreService instance;

    public static GenreService getInstance() {
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
