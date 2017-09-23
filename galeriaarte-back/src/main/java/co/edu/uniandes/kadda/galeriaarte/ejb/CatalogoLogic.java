/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;


import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.CatalogoPersistence;
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

    
    
}
