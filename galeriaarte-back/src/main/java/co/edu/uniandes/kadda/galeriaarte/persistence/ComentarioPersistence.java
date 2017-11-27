package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ks.estupinan
 */
@Stateless
public class ComentarioPersistence {

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    /**
     * @param entity
     * @return ComentarioEntity
     */
    public ComentarioEntity create(ComentarioEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * @param id
     * @return ComentarioEntity
     */
    public ComentarioEntity find(Long id) {
        return em.find(ComentarioEntity.class, id);
    }

    /**
     * @return ComentarioEntity
     */
    public List<ComentarioEntity> findAll() {
        Query q = em.createQuery("select u from ComentarioEntity u");
        return q.getResultList();
    }

    /**
     * @param entity
     * @return ComentarioEntity
     */
    public ComentarioEntity update(ComentarioEntity entity) {
        return em.merge(entity);
    }

    /**
     * @param id
     */
    public void delete(Long id) {
        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        em.remove(entity);
    }
}
