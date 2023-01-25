package groupwork.dao.db.fabrics;

import groupwork.dao.db.SingerDAO_DB;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.orm.factory.EntityManagerVoteSingleton;

import java.beans.PropertyVetoException;

public class SingerDaoDBSingleton {
    private volatile static SingerDAO_DB instance;

    private SingerDaoDBSingleton() {
    }

    public static ISingerDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (SingerDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new SingerDAO_DB(EntityManagerVoteSingleton.getInstance());
                }
            }
        }
        return instance;
    }


}
