package groupwork.dao.db;


import groupwork.dao.api.IGenreDao;
import groupwork.dao.orm.manager.Manager;
import groupwork.entity.GenreEntity;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDAO_DB implements IGenreDao {
    private Manager manager;

    public GenreDAO_DB(Manager manager) {
        this.manager = manager;
    }

    @Override
    public List<GenreEntity> getGenreList() {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> query = cb.createQuery(GenreEntity.class);
        Root<GenreEntity> c = query.from(GenreEntity.class);
        query.select(c);
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        if (genreEntity != null) {
            result = true;
        }
        return result;
    }

    @Override
    public void delete(GenreEntity genreEntity) {
        int id = genreEntity.getId();

        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        genreEntity = entityManager.find(GenreEntity.class, id);
        entityManager.remove(genreEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void create(GenreEntity genreEntity) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(genreEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(GenreEntity genreEntity) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(genreEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public GenreEntity get(Integer id) {
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();
        GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
        entityManager.getTransaction().commit();;
        entityManager.close();

        return genreEntity;
    }
}
