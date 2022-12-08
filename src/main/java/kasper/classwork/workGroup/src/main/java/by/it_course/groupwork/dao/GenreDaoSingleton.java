package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.GenreDao;


public class GenreDaoSingleton {
    private volatile static GenreDao instance;

    public static GenreDao getInstance(){
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
