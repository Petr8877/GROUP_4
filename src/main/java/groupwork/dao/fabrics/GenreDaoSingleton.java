package groupwork.dao.fabrics;

import groupwork.dao.DB.GenreDaoDB;
import groupwork.dao.api.IGenreDao;

public class GenreDaoSingleton {
//    private volatile static GenreDao instance;
private volatile static GenreDaoDB instance;

    private GenreDaoSingleton() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoSingleton.class) {
                if (instance == null) {
//                    instance = new GenreDao();
                    instance = new GenreDaoDB();
                }
            }
        }
        return instance;
    }
}
