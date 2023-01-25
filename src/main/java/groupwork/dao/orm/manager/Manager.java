package groupwork.dao.orm.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {
    private EntityManagerFactory factory;

    public Manager() {
        this.factory = Persistence.createEntityManagerFactory("groupwork");
    }

    public EntityManager getEntityManager(){
        return this.factory.createEntityManager();
    }
}
