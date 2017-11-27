package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
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
public class ClientePersistence {

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    /**
     * @param entity
     * @return ClienteEntity
     */
    public ClienteEntity create(ClienteEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * @param id
     * @return ClienteEntity
     */
    public ClienteEntity find(Long id) {
        return em.find(ClienteEntity.class, id);
    }

    /**
     * @return ClienteEntity
     */
    public List<ClienteEntity> findAll() {
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }

    /**
     * @param entity
     * @return ClienteEntity
     */
    public ClienteEntity update(ClienteEntity entity) {
        return em.merge(entity);
    }

    /**
     * @param id
     */
    public void delete(Long id) {
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
}
