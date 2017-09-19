/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.carrillor
 */
@Stateless
public class ArtistaLogic 
{
 @Inject
    private ArtistaPersistence persistence; 

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ArtistaEntity createArtista(ArtistaEntity entity) throws BusinessLogicException 
    {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un id con el id \"" + entity.getId() + "\"");
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
    public List<ArtistaEntity> getArtistas() {
        
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ArtistaEntity> artistas = persistence.findAll();
        
        return artistas;
    }
    
    public ArtistaEntity findArtista(Long id)
    {
        List<ArtistaEntity> artistas = persistence.findAll();
        for(int i=0;i<artistas.size();i++)
        {
            ArtistaEntity actual =artistas.get(i);
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
    
    
    public void update(ArtistaEntity entity)
    {
        
        ArtistaEntity x = findArtista(entity.getId());
        if (x!=null)
        {
            persistence.update(x);
        }
        
    }
    
}
