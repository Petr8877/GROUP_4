package groupwork.dao.orm.factory;

import groupwork.dao.orm.manager.Manager;

public class EntityManagerVoteSingleton {
    private volatile static Manager instance;

    private EntityManagerVoteSingleton() {
    }

    public static Manager getInstance() {
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
