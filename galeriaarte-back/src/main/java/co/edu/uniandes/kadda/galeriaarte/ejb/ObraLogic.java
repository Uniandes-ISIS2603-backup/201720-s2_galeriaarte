/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ObraPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.carrillor
 */
@Stateless
public class ObraLogic {

     @Inject
 private ObraPersistence persistence; 
 
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ObraEntity createObra(ObraEntity entity) throws BusinessLogicException 
    {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una obra con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la Estudiante
        persistence.create(entity);
        return entity;
    }

    /**
     * 
     * Obtener todas las Estudiantees existentes en la base de datos.
     *
     * @return una lista de Estudiantees.
     */
    public List<ObraEntity> getObras() {
        
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ObraEntity> obras = persistence.findAll();
        
        return obras;
    }
    
    public ObraEntity findObra(Long id)
    {
        List<ObraEntity> obras = persistence.findAll();
        for(int i=0;i<obras.size();i++)
        {
            ObraEntity actual =obras.get(i);
            if (actual.getId().equals(id))
            {
              return actual;  
            }
        }
        return null;
    }
    
    public void delete(Long id)
    {
       persistence.delete(id);
    }
    
    
    public void update(ObraEntity entity)
    {
        
        ObraEntity x = findObra(entity.getId());
        if (x!=null)
        {
            persistence.update(x);
        }
        
    }
    
    
    
     public ArtistaEntity getAuthor(Long bookId, Long authorsId) {
       
        ArtistaEntity artista = findObra(bookId).getArtista();
        
        return artista;
    }
 
}
