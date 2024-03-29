package groupwork.dao.memory.fabrics;

import groupwork.dao.memory.GenreDao;
import groupwork.dao.api.IGenreDao;

public class GenreDaoSingleton {
    private volatile static GenreDao instance;

    private GenreDaoSingleton() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoSingleton.class) {
                if (instance == null) {
                    instance = new GenreDao();
                }
            }
        }
        return instance;
    }
}
