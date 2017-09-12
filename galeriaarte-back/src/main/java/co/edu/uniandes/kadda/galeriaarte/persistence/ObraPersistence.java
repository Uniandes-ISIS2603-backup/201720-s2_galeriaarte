/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
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
public class ObraPersistence 
{
    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    
    public ObraEntity create(ObraEntity entity) {
        
     
        em.persist(entity);
        
        return entity;
    }

   
    public ObraEntity update(ObraEntity entity)
    {
        
        
        return em.merge(entity);
    }

    
    public void delete(Long id) {
       
        
        ObraEntity entity = em.find(ObraEntity.class, id);
        
        em.remove(entity);
    }

   
    public ObraEntity find(Long id) {
        
       
        return em.find(ObraEntity.class, id);
    }

    
    public List<ObraEntity> findAll() {
        
       
       Query query = em.createQuery("select u from ObraEntity u");
        
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
