package groupwork.dao.db.fabrics;

import groupwork.dao.db.GenreDAO_DB;
import groupwork.dao.api.IGenreDao;
import groupwork.dao.db.ds.fabrics.DataSourceSingleton;
import groupwork.dao.orm.factory.EntityManagerVoteSingleton;

import java.beans.PropertyVetoException;

public class GenreDaoDBSingleton {
    private volatile static GenreDAO_DB instance;

    private GenreDaoDBSingleton() {
    }

    public static IGenreDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (GenreDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new GenreDAO_DB(EntityManagerVoteSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
