/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ma.abril
 */
@Stateless
public class CompraPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CompraPersistence.class.getName());

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    public CompraEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando compra con id={0}", id);
        return em.find(CompraEntity.class, id);
    }


    public List<CompraEntity> findAll() {
        LOGGER.info("Consultando todas las compras");
        Query q = em.createQuery("select u from CompraEntity u");
        return q.getResultList();
    }

    public CompraEntity create(CompraEntity entity) {
        LOGGER.info("Creando una compra nueva");
        em.persist(entity);
        LOGGER.info("Compra creada");
        return entity;
    }

    public CompraEntity update(CompraEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando compra con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando compra con id={0}", id);
        CompraEntity entity = em.find(CompraEntity.class, id);
        em.remove(entity);
    }
    
}
