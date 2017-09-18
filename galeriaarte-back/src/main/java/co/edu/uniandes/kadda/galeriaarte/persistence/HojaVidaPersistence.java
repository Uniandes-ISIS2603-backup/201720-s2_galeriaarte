/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;



import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
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
public class HojaVidaPersistence 
{
   

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;

    
    public HojaVidaEntity create(HojaVidaEntity entity) {
        
       
        em.persist(entity);
       
        return entity;
    }

   
    public HojaVidaEntity update(HojaVidaEntity entity) {
       
        return em.merge(entity);
    }

   
    public void delete(Long id) {
       
        HojaVidaEntity entity = em.find(HojaVidaEntity.class, id);
        
        em.remove(entity);
    }

   
    public HojaVidaEntity find(Long id) {
       
        return em.find(HojaVidaEntity.class, id);
    }

    
    public List<HojaVidaEntity> findAll() {
       
        Query query = em.createQuery("select u from HojaVidaEntity u");
        
        return query.getResultList();
    }

   
    /*/
    public HojaVidaEntity findByCodigo(String codigo) {
        LOGGER.log(Level.INFO, "Consultando Estudiante por código ", codigo);

        // Se crea un query para buscar Estudiantees con el codigo que recibe el método como argumento. ":codigo" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EstudianteEntity e where e.codigo = :codigo", HojaVidaEntity.class);
        // Se remplaza el placeholder ":codigo" con el valor del argumento 
        query = query.setParameter("codigo", codigo);
        // Se invoca el query se obtiene la lista resultado
        List<HojaVidaEntity> sameCodigo = query.getResultList();
        if (sameCodigo.isEmpty()) {
            return null;
        } else {
            return sameCodigo.get(0);
        }
    }
/*/
}
