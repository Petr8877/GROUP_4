package groupwork.dao.memory.fabrics;

import groupwork.dao.memory.SingerDao;
import groupwork.dao.api.ISingerDao;

public class SingerDaoSingleton {
    private volatile static SingerDao instance;

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
