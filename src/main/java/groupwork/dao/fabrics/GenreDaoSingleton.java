package groupwork.dao.fabrics;

import groupwork.dao.GenreDao;
import groupwork.dao.GenreDaoDB;
import groupwork.dao.api.IGenreDao;

public class GenreDaoSingleton {
    private volatile static IGenreDao instance;

    private GenreDaoSingleton() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoSingleton.class) {
                if (instance == null) {
                    instance = new GenreDaoDB();
                }
            }
        }
        return instance;
    }
}
