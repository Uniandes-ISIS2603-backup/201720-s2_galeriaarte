/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author af.leon
 */
@Stateless


public class BlogPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;
    
    public BlogEntity create(BlogEntity entity) {
        LOGGER.info("Creando un blog nuevo");
        em.persist(entity);
        LOGGER.info("Blog creado");
        return entity;
    }

    public BlogEntity update(BlogEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando blog con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando blog con id={0}", id);
        BlogEntity entity = em.find(BlogEntity.class, id);
        em.remove(entity);
    }

    public BlogEntity find(Long artistaid, Long blogid) {
        TypedQuery<BlogEntity> q = em.createQuery("select p from BlogEntity p where (p.artista.id = :artistaid) and (p.id = :blogid)", BlogEntity.class);
        q.setParameter("artistaid", artistaid);
        q.setParameter("blogid", blogid);
        List<BlogEntity> results = q.getResultList();
        BlogEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }

        return review;
    }
    
}
