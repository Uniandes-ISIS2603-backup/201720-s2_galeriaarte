/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.PagoPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.CompraPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ma.abril
 */
@Stateless
public class PagoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;

    @Inject
    private CompraPersistence compraPersistence;

    /**
     * Obtiene la lista de los registros de Pago que pertenecen a un compra.
     *
     * @return Colección de objetos de PagoEntity.
     * @throws co.edu.uniandes.csw.comprastore.exceptions.BusinessLogicException
     */
    public List<PagoEntity> getPagos() throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        List<PagoEntity> pagos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los pagos");
        return pagos;
    }

    /**
     * Obtiene los datos de una instancia de Pago a partir de su ID.
     *
     * @param compraid
     * @pre La existencia del elemento padre compra se debe garantizar.
     * @param pagoid) Identificador del Pago a consultar
     * @return Instancia de PagoEntity con los datos del Pago consultado.
     * 
     */
    public PagoEntity getPago(Long compraid, Long pagoid) {
        return persistence.find(compraid, pagoid);
    }

    /**
     * Se encarga de crear un Pago en la base de datos.
     *
     * @param entity Objeto de PagoEntity con los datos nuevos
     * @param compraid id del compra el cual sera padre del nuevo Pago.
     * @return Objeto de PagoEntity con los datos nuevos y su ID.
     * 
     */
    public PagoEntity createPago(Long compraid, PagoEntity entity) {
        LOGGER.info("Inicia proceso de crear Pago");
        CompraEntity compra = compraPersistence.find(compraid);
        entity.setCompra(compra);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Pago.
     *
     * @param entity Instancia de PagoEntity con los nuevos datos.
     * @param compraid id del compra el cual sera padre del Pago actualizado.
     * @return Instancia de PagoEntity con los datos actualizados.
     * 
     */
    public PagoEntity updatePago(Long compraid, PagoEntity entity) {
        LOGGER.info("Inicia proceso de actualizar Pago");
        CompraEntity compra = compraPersistence.find(compraid);
        entity.setCompra(compra);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Pago de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param compraid id del compra el cual es padre del Pago.
     * 
     */
    public void deletePago(Long compraid, Long id) {
        LOGGER.info("Inicia proceso de borrar Pago");
        PagoEntity old = getPago(compraid, id);
        persistence.delete(old.getId());
    }
   

}
