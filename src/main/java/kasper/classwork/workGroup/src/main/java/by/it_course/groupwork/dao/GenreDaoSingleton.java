package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.GenreDao;
import by.it_course.groupwork.dao2.api.IGenreDao;

public class GenreDaoSingleton {
    private volatile static GenreDao instance;

    private GenreDaoSingleton() {
    }

    public static IGenreDao getInstance(){
        if(instance == null){
            synchronized (GenreDaoSingleton.class){
                if(instance==null){
                    instance = new GenreDao();
                }
            }
        }
        return instance;
    }
}
