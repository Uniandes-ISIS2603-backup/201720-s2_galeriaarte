/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import java.util.List;
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
    


    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    
    public ArtistaEntity create(ArtistaEntity entity) {
        
     
        em.persist(entity);
        
        return entity;
    }

   
    public ArtistaEntity update(ArtistaEntity entity)
    {
        
        
        return em.merge(entity);
    }

    
    public void delete(Long id) {
       
        
        ArtistaEntity entity = em.find(ArtistaEntity.class, id);
        
        em.remove(entity);
    }

   
    public ArtistaEntity find(Long id) {
        
       
        return em.find(ArtistaEntity.class, id);
    }

    
    public List<ArtistaEntity> findAll() {
        
       
       Query query = em.createQuery("select u from ArtistaEntity u");
        
        return query.getResultList();
    }

    /*/
    public ArtistaEntity findByCodigo(String codigo) {
        

        
        TypedQuery query = em.createQuery("Select e From ArtistaEntity e where e.codigo = :codigo", ArtistaEntity.class);
        
        query = query.setParameter("codigo", codigo);
        
        List<ArtistaEntity> sameCodigo = query.getResultList();
        if (sameCodigo.isEmpty()) {
            return null;
        } else {
            return sameCodigo.get(0);
        }
    }
/*/
}
