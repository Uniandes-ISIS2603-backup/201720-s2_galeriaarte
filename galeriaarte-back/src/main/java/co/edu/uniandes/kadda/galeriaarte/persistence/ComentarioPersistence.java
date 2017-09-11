/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    //private static final Logger LOGGER = Logger.getLogger(CompanyPersistence.class.getName());
    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    public ComentarioEntity create(ComentarioEntity entity) {
        //LOGGER.info("Creando un Comany nueva");
        em.persist(entity);
        //LOGGER.info("Company creada");
        return entity;
    }

    public ComentarioEntity find(Long id) {
        //LOGGER.log(Level.INFO, "Consultando employee con id={0}", id);
        return em.find(ComentarioEntity.class, id);
    }

    public List<ComentarioEntity> findAll() {
        //LOGGER.info("Consultando todos los employees");
        Query q = em.createQuery("select u from ComentarioEntity u");
        return q.getResultList();
    }

    public ComentarioEntity update(ComentarioEntity entity) {
        //LOGGER.log(Level.INFO, "Actualizando employee con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        //LOGGER.log(Level.INFO, "Borrando employee con id={0}", id);
        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        em.remove(entity);
    }
}
