/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
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
public class CompraLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CompraLogic.class.getName());

    @Inject
    private CompraPersistence persistence;
    
  public List<CompraEntity> getCompras() {
        LOGGER.info("Inicia proceso de consultar todas las compras");
        List<CompraEntity> compras = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las compras");
        return compras;
    }

    public CompraEntity getCompra(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar compra con id={0}", id);
        CompraEntity compra = persistence.find(id);
        if (compra == null) {
            LOGGER.log(Level.SEVERE, "La compra con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar compra con id={0}", id);
        return compra;
    }

    public CompraEntity createCompra(CompraEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de compra");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una compra con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la editorial

        LOGGER.info("Termina proceso de creación de compra");
        return persistence.create(entity);
    }
    /**
     * Actualiza la información de una instancia de Pago.
     *
     * @param entity Instancia de PagoEntity con los nuevos datos.
     * @param Compraid id del Compra el cual sera padre del Pago actualizado.
     * @return Instancia de PagoEntity con los datos actualizados.
     * 
     */
   public CompraEntity updateCompra(Long id, CompraEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar compra con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        CompraEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar compra con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteCompra(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar compra con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar compra con id={0}", id);
    }
    
//        /**
//         * Agregar un Obra a la compra
//         *
//         * @param ObraId del Obra a asociar
//         * @param compraId
//         * @return
//         */
//    public ObraEntity addObra(Long ObraId, Long compraId) {
//        CompraEntity compraEntity = getCompra(compraId);
//        ObraEntity ObraEntity = ObraLogic.getObra(ObraId);
//        ObraEntity.setCompra(compraEntity);
//        return ObraEntity;
//    }
//
//    /**
//     * Borrar un Obra de una compra
//     *
//     * @param ObraId
//     * @param compraId
//     */
//    public void removeObra(Long ObraId, Long compraId) {
//        CompraEntity compraEntity = getCompra(compraId);
//        ObraEntity Obra = ObraLogic.getObra(ObraId);
//        Obra.setcompra(null);
//        compraEntity.getObras().remove(Obra);
//    }
//
//    /**
//     * Remplazar Obras de una compra
//     *
//     * @param Obras
//     * @param compraId
//     * @return
//     */
//    public List<ObraEntity> replaceObras(Long compraId, List<ObraEntity> Obras) {
//        CompraEntity compra = getCompra(compraId);
//        List<ObraEntity> ObraList = ObraLogic.getObras();
//        for (ObraEntity Obra : ObraList) {
//            if (Obras.contains(Obra)) {
//                Obra.setCompra(compra);
//            } else if (Obra.getCompra() != null && Obra.getCompra().equals(compra)) {
//                Obra.setCompra(null);
//            }
//        }
//        return Obras;
//    }
//
//    /**
//     * Retorna todos los Obras asociados a una compra
//     *
//     * @param compraId
//     * @return
//     */
//    public List<ObraEntity> getObras(Long compraId) {
//        return getCompra(compraId).getObras();
//    }
//
//    /**
//     * Retorna un Obra asociado a una compra
//     *
//     * @param compraId
//     * @param ObraId
//     * @return
//     * @throws BusinessLogicException
//     */
//    public ObraEntity getObra(Long compraId, Long ObraId) throws BusinessLogicException {
//        List<ObraEntity> Obras = getCompra(compraId).getObras();
//        ObraEntity Obra = ObraLogic.getObra(ObraId);
//        int index = Obras.indexOf(Obra);
//        if (index >= 0) {
//            return Obras.get(index);
//        }
//        throw new BusinessLogicException("La obra no está asociado a la compra");
//
//    }

    /**
     * Obtiene una colección de instancias de ObraEntity asociadas a una
     * instancia de compra
     *
     * @param compraId Identificador de la instancia de compra
     * @return Colección de instancias de ObraEntity asociadas a la instancia de
     * compra
     *
     */
    public List<ObraEntity> listObras(Long compraId) {
        return getCompra(compraId).getObras();
    }

}
