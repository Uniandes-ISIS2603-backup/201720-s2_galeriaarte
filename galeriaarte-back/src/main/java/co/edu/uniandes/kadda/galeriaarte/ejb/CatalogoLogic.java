/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;


import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.CatalogoPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.GaleriaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.ObraPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Perilla
 */
@Stateless
public class CatalogoLogic 
{
 
     private static final Logger LOGGER = Logger.getLogger(CatalogoLogic.class.getName());


    @Inject
    private CatalogoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ObraPersistence persistenceObr; //Variable para acceder a la persistencia de la aplicación. 
    
    @Inject
    private GaleriaPersistence persistenceGal;
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public CatalogoEntity crearCatalogo(CatalogoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del Catalogo");
        // Invoca la persistencia para crear el Catalogo
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Galeria");
        return entity;
    }

    /**
     * 
     * Obtener todas los Catalogos existentes en la base de datos.
     *
     * @return una lista de Catalogos.
     */
    public List<CatalogoEntity> getCatalogos() {
        LOGGER.info("Inicia proceso de consultar todos los Catalogos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CatalogoEntity> catalogos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Catalogos");
        return catalogos;
    }

    public CatalogoEntity getCatalogo(Long id) 
    {
        LOGGER.info("Inicia proceso de consultar un solo catalogo");
        CatalogoEntity catalogo = persistence.find(id);
        LOGGER.info("Termina proceso de consultar un solo catálogo");
        return catalogo;
        
    }

    public CatalogoEntity updateCatalogo(Long id, CatalogoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar catalogo con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        CatalogoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar catalogo con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteCatalogo(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminación catalogo con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de eliminzación catalogo con id = {0]", id);
    }

    /**
     * Obtiene una colección de instancias de ObraEntity asociadas a una
     * instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @return Colección de instancias de ObraEntity asociadas a la instancia de Catalogo
     * @generated
     */
    public List<ObraEntity> listObras(Long catalogoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las obras del catalogo con id = {0}", catalogoId);
        return getCatalogo(catalogoId).getObras();
        
    }
    
    /**
     * Obtiene una instancia de ObraEntity asociada a una instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @param obraId Identificador de la instancia de Obra
     * @return
     * @generated
     */
    public ObraEntity getObra(Long catalogoId, Long obraId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una obra con id = {0}", obraId);
        List<ObraEntity> list = getCatalogo(catalogoId).getObras();
        ObraEntity obraEntity = new ObraEntity();
        for (int i = 0; i < list.size(); i++)
        {
            if((long)list.get(i).getId() == obraId)
            {
                obraEntity = list.get(i);
            }
        }
        return obraEntity;

    }

    /**
     * Asocia una Obra existente a un Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @param obraId Identificador de la instancia de Obra
     * @return Instancia de ObraEntity que fue asociada a Catalogo
     * @generated
     */
    public ObraEntity addObra(Long catalogoId, Long obraId) 
    {
       CatalogoEntity catalogo = persistence.find(catalogoId);
       ObraEntity obra = persistenceObr.find(obraId);
       List<ObraEntity> obras = catalogo.getObras();
       obras.add(obra);
       catalogo.setObras(obras);
       obra.setCatalogo(catalogo);
       return obra;
       
    }
        
        
    /**
     * Remplaza las instancias de Obra asociadas a una instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de catalogo
     * @param list Colección de instancias de ObraEntity a asociar a instancia
     * de Catalogo
     * @return Nueva colección de ObraEntity asociada a la instancia de Catalogo
     * @generated
     */
    public List<ObraEntity> replaceObras(Long catalogoId, List<ObraEntity> list)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar las obras asocidos al catalogo con id = {0}", catalogoId);
        CatalogoEntity catalogoEntity = getCatalogo(catalogoId);
        List<ObraEntity> obraList = catalogoEntity.getObras();
       
        for (int i = 0; i < obraList.size(); i++) 
        {
            if(list.contains(obraList.get(i)))
            {
                
            }
            else
            {
                obraList.get(i).setCatalogo(null);
            }
        }   
        catalogoEntity.setObras(list);
        return catalogoEntity.getObras();
    }

    /**
     * Desasocia una Obra existente de un Catalogo existente
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @param obraId Identificador de la instancia de Obra
     * @generated
     */
    public void removeObra(Long catalogoId, Long obraId)
    {
        CatalogoEntity catalogo = persistence.find(catalogoId);
        ObraEntity obra = persistenceObr.find(obraId);
        
        List<ObraEntity> obras = catalogo.getObras();
        
        for (int i = 0; i < obras.size(); i++)
        {
            if(obras.get(i) == obra)
            {
               obras.remove(obra);
            }
        }
        catalogo.setObras(obras);
    }
    
    /**
     * No solo agrega una galería a un catálogo sino que también agrega un catálogo a la galería
     */


    public GaleriaEntity addGaleria(Long catalogoId, Long galeriaId) 
    {
       CatalogoEntity catalogo = persistence.find(catalogoId);
       GaleriaEntity galeria = persistenceGal.find(galeriaId);
       catalogo.setGaleria(galeria);
       List<CatalogoEntity> catalogos = galeria.getCatalogos();
       catalogos.add(catalogo);
       return galeria;
    }
    
    
}
