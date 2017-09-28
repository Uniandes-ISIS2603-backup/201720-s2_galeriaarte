/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import java.util.List;
import java.util.logging.Level;
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
        ArtistaEntity artistas = persistence.find(id);
       return artistas;
    }
    
    public ArtistaEntity findArtista(int id)
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
    
    
    public ArtistaEntity update(ArtistaEntity entity)
    {
        
        ArtistaEntity x = findArtista(entity.getId());
        if (x!=null)
        {
           return persistence.update(x);
        }
        return null;
    }
    

    public HojaVidaEntity getHojaVida(Long artistaId) {
       
    
        return findArtista(artistaId).getHojaVida();
        
      
    }
   /*/
    public HojaVidaEntity addHojaVida(Long artistaId, Long hojaVidaId) 
         
    {
       ArtistaEntity artista = findArtista(artistaId);
       HojaVidaEntity hoja = hojaVidaLogic.findHoja(hojaVidaId);
       if(artista!= null && hoja!=null)
       {
           artista.setHojaVida(hoja);
       }
        return hoja;
    }
    /*/
     public HojaVidaEntity replaceHojaVida(Long artistaId, HojaVidaEntity hoja) {
        
       ArtistaEntity artista = findArtista(artistaId);
       HojaVidaEntity hojaNueva = hoja;
       
       if(artista!= null && hoja!=null)
       {
           artista.setHojaVida(hoja);
       }
        return hojaNueva;
    }
     
      public void removeHojaVida(Long artistaId)
      {
       ArtistaEntity artista = findArtista(artistaId);
          if (artista!=null)
          {
           artista.setHojaVida(null);
          }
       
        
    }
      
      public List<ObraEntity> listObras(Long authorId)
      {
        return findArtista(authorId).getObras();
      }
      
      public ObraEntity getObra(Long authorId, Long obraId) {
        
        List<ObraEntity> list = findArtista(authorId).getObras();
        ObraEntity obraEntity = new ObraEntity();
        obraEntity.setId(obraId);
        int index = list.indexOf(obraEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

      
      
      
    
}
