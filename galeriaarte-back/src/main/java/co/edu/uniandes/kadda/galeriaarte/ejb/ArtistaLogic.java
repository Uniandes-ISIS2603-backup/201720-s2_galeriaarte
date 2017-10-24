/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.BlogPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.GaleriaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.HojaVidaPersistence;
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
public class ArtistaLogic 
{
    
private static final Logger LOGGER = Logger.getLogger(ArtistaLogic.class.getName());
    
 @Inject
 private ArtistaPersistence persistence;
 
 @Inject 
 private ObraPersistence obraPersistence;
 
 @Inject
 private HojaVidaPersistence hojaVidaPersistence;
 
@Inject
 private GaleriaPersistence galeriaPersistence;

@Inject
private BlogPersistence blogPersistence;

 
     public ArtistaEntity createArtista(ArtistaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de artista");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de artista");
        return entity;
    }
     

    public List<ArtistaEntity> getArtistas() {
        LOGGER.info("Inicia proceso de consultar todos los artistas");
        List<ArtistaEntity> artistas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los artistas");
        return artistas;
    }
    
    public ArtistaEntity getArtista(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar artista con id={0}", id);
        ArtistaEntity artista = persistence.find(id);
        if (artista == null) {
            LOGGER.log(Level.SEVERE, "El artista con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar artista con id={0}", id);
        return artista;
    }
    
   public void deleteArtista(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar artista con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar artista con id={0}", id);
    }
   
    public ArtistaEntity updateArtista(Long id, ArtistaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar artista con id={0}", id);
        ArtistaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar artista con id={0}", entity.getId());
        return newEntity;
    }
    

    public HojaVidaEntity getHojaVida(Long artistaId) {
        return getArtista(artistaId).getHojaVida();
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
        
       ArtistaEntity artista = getArtista(artistaId);
       HojaVidaEntity hojaNueva = hoja;
       
       if(artista!= null && hoja!=null)
       {
           artista.setHojaVida(hoja);
       }
        return hojaNueva;
    }
     
      public void removeHojaVida(Long artistaId)
      {
       ArtistaEntity artista = getArtista(artistaId);
          if (artista!=null)
          {
           artista.setHojaVida(null);
          }
       
        
    }
      
      public List<ObraEntity> listObras(Long authorId)
      {
        return getArtista(authorId).getObras();
      }
      
      public List<BlogEntity> listBlogs(Long authorId)
      {
        return getArtista(authorId).getBlogs();
      }
      
      public ObraEntity getObra(Long authorId, Long obraId) {
        
        List<ObraEntity> list = getArtista(authorId).getObras();
        ObraEntity obraEntity = new ObraEntity();
        obraEntity.setId(obraId);
        int index = list.indexOf(obraEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
      
     
      public ObraEntity addObra(Long artistaId, ObraEntity obra) throws BusinessLogicException
      {
        ArtistaEntity artista = getArtista(artistaId);
        ObraEntity obraNueva= createObra(obra);
        obraNueva.setArtista(artista);
        List<ObraEntity> obrasArtista = artista.getObras();
        obrasArtista.add(obra);
        return obraNueva;
    }
      
      public ObraEntity createObra(ObraEntity entity) throws BusinessLogicException 
      {
      if (obraPersistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una obra con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la Estudiante
        obraPersistence.create(entity);
        return entity;
      }
      
      
      
      public HojaVidaEntity addHoja(Long artistaId, HojaVidaEntity hoja) throws BusinessLogicException
      {
        ArtistaEntity artista = getArtista(artistaId);
        HojaVidaEntity hojaNueva= createHojaVida(hoja);
        hojaNueva.setArtista(artista);
        return hojaNueva;
      }
      
       public HojaVidaEntity createHojaVida(HojaVidaEntity entity) throws BusinessLogicException 
    {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre
        if (hojaVidaPersistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Hoja de vida con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la Estudiante
        hojaVidaPersistence.create(entity);
        return entity;
    }
      
      
      public GaleriaEntity addGaleria(Long artistaId, GaleriaEntity gal) throws BusinessLogicException
      {
        ArtistaEntity artistaEntity = getArtista(artistaId);
        GaleriaEntity galNueva = createGaleria(gal);
        List<ArtistaEntity> listaArtistas = galNueva.getArtistas();
        listaArtistas.add(artistaEntity);
        artistaEntity.setGaleria(gal);
        
        return galNueva;
          
        
      }
      
      public GaleriaEntity createGaleria(GaleriaEntity entity) throws BusinessLogicException {
        
        galeriaPersistence.create(entity);
       
        return entity;
    }

      
      public ObraEntity replaceObra(Long artistaId, Long obraId)
      {
          return null;
      }
     
}
