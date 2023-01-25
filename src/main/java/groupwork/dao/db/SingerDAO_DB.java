package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.orm.manager.Manager;
import groupwork.entity.SingerEntity;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingerDAO_DB implements ISingerDao {
    private final Manager manager;

    public SingerDAO_DB(Manager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getSingerList() {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        List<SingerEntity> resultList = entityManager.createQuery("from SingerEntity", SingerEntity.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        if(singerEntity != null) {
            result = true;
        }

        return result;
    }

    @Override
    public void delete(SingerEntity singerEntity) {
        int id = singerEntity.getId();

        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        singerEntity = entityManager.find(SingerEntity.class, id);
        entityManager.remove(singerEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void create(SingerEntity singerEntity) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(singerEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(SingerEntity singerEntity) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(singerEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Override
    public SingerEntity get(int id) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
        entityManager.getTransaction().commit();;
        entityManager.close();

        return singerEntity;
    }
}
