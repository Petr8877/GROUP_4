package groupwork.dao.db;

import groupwork.dao.api.IVotingDao;
import groupwork.dao.orm.api.IManager;
import groupwork.entity.SavedVoice;

import javax.persistence.EntityManager;
import java.util.List;

public class VotingDAO_DB implements IVotingDao {
    private final IManager manager;

    public VotingDAO_DB(IManager manager) {
        this.manager = manager;
    }



    @Override
    public List<SavedVoice> getVoiceList() {
        EntityManager entityManager = null;
        List<SavedVoice> savedVoices;
        try {
            entityManager = manager.getEntityManager();

            entityManager.getTransaction().begin();
            savedVoices  = entityManager.createQuery("FROM SavedVoice", SavedVoice.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("SQL exception", e.getCause());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return savedVoices;

    }

    @Override
    public void save(SavedVoice voice) {

        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(voice);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            throw new RuntimeException("SQL exception", e.getCause());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
