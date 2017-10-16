/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author jd.carrillor
 */
@Stateless
public class ArtistaPersistence {
   
    private static final Logger LOGGER = Logger.getLogger(ArtistaPersistence.class.getName());
    
    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    public ArtistaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando artista con id={0}", id);
        return em.find(ArtistaEntity.class, id);
    }

    public List<ArtistaEntity> findAll() {
        LOGGER.info("Consultando todos los artistas");
        Query q = em.createQuery("select u from ArtistaEntity u");
        return q.getResultList();
    }

    public ArtistaEntity create(ArtistaEntity entity) {
        LOGGER.info("Creando un artista nuevo");
        em.persist(entity);
        LOGGER.info("Artista creado");
        return entity;
    }

    public ArtistaEntity update(ArtistaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando artista con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando artista con id={0}", id);
        ArtistaEntity entity = em.find(ArtistaEntity.class, id);
        em.remove(entity);
    }
    
}
