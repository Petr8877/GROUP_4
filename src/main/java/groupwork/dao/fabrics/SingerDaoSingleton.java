package groupwork.dao.fabrics;

import groupwork.dao.SingerDao;
import groupwork.dao.SingerDaoDB;
import groupwork.dao.api.ISingerDao;

public class SingerDaoSingleton {
    private volatile static ISingerDao instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance() {
        if (instance == null) {
            synchronized (SingerDaoSingleton.class) {
                if (instance == null) {
                    instance = new SingerDao();
                }
            }
        }
        return instance;
    }


}
