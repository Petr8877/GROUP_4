package groupwork.dao.orm.factory;

import groupwork.dao.orm.api.IManager;
import groupwork.dao.orm.manager.Manager;

public class EntityManagerVoteSingleton {
    private volatile static IManager instance;

    private EntityManagerVoteSingleton() {
    }

    public static IManager getInstance() {
        if (instance == null) {
            synchronized (EntityManagerVoteSingleton.class){
                if(instance == null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }
}
