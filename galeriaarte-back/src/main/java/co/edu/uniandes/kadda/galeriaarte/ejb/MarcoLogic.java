/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.MarcoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.leon
 */
@Stateless
public class MarcoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MarcoLogic.class.getName());
    
    @Inject
    private MarcoPersistence persistence;
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public MarcoEntity createMarco(MarcoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor ");
        
        return persistence.create(entity);
    }
    
     /**
     * Borrar un marco
     *
     * @param id: id del marco a borrar
     */
    public void deleteMarco(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el marco con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el marco con id={0}", id);
    }
    
    /**
     *
     * Obtener todas los marcos existentes en la base de datos.
     *
     * @return una lista de marcos.
     */
    public List<MarcoEntity> getMarcos() {
        LOGGER.info("Inicia proceso de consultar todos los marcos");
        List<MarcoEntity> marcos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los marcos");
        return marcos;
    }
    
    /**
     *
     * Obtener un marco por medio de su id.
     *
     * @param id: id del marco para ser buscada.
     * @return el marco solicitada por medio de su id.
     */
    public MarcoEntity getMarco(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar marco con id={0}", id);
        MarcoEntity marco = persistence.find(id);
        if (marco == null) {
            LOGGER.log(Level.SEVERE, "El marco con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar marco con id={0}", id);
        return marco;
    }
    
    /**
     *
     * Actualizar un marco.
     *
     * @param id: id del marco para buscarla en la base de datos.
     * @param entity: marco con los cambios para ser actualizada, por
     * ejemplo el ancho.
     * @return el marco con los cambios actualizados en la base de datos.
     */
    public MarcoEntity updateMarco(Long id, MarcoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar marco con id={0}", id);
        
        MarcoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar marco con id={0}", entity.getId());
        return newEntity;
    }
    
    
    public MarcoEntity updateMarco(MarcoEntity entity) {
       
        
        MarcoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar marco con id={0}", entity.getId());
        return newEntity;
    }
    
}
