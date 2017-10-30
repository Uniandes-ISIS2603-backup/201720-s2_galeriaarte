/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ma.abril
 */
@Stateless
public class PagoPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

   public PagoEntity find(Long compraid, Long pagoid) {
        TypedQuery<PagoEntity> q = em.createQuery("select p from PagoEntity p where (p.compra.id = :compraid) and (p.id = :pagoid)", PagoEntity.class);
        q.setParameter("compraid", compraid);
        q.setParameter("pagoid", pagoid);
        List<PagoEntity> results = q.getResultList();
        PagoEntity pago = null;
        if (results == null) {
            pago = null;
        } else if (results.isEmpty()) {
            pago = null;
        } else if (results.size() >= 1) {
            pago = results.get(0);
        }

        return pago;
    }

    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todos los pagos");
        Query q = em.createQuery("select u from PagoEntity u");
        return q.getResultList();
    }

    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando un pago nuevo");
        em.persist(entity);
        LOGGER.info("Pago realizado");
        return entity;
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando pago con id={0}", id);
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
}
