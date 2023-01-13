package groupwork.dao.fabrics;

import groupwork.dao.DB.SingerDaoDB;
import groupwork.dao.api.ISingerDao;

public class SingerDaoSingleton {
//    private volatile static SingerDao instance;
private volatile static SingerDaoDB instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance() {
        if (instance == null) {
            synchronized (SingerDaoSingleton.class) {
                if (instance == null) {
//                    instance = new SingerDao();
                    instance = new SingerDaoDB();
                }
            }
        }
        return instance;
    }


}
