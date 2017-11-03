/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CompraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * URI: compras/{comprasId: \\d+}/obras
 *
 * @author ma.abril
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompraObrasResource {
    
    @Inject
    private CompraLogic compraLogic;
    
    /**
     * Convierte una lista de ObraEntity a una lista de ObraDetailDTO.
     *
     * @param entityList Lista de ObraEntity a convertir.
     * @return Lista de ObraDetailDTO convertida.
     * 
     */
    private List<ObraDetailDTO> ObrasListEntity2DTO(List<ObraEntity> entityList) {
        List<ObraDetailDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) {
            list.add(new ObraDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ObraDetailDTO a una lista de ObraEntity.
     *
     * @param dtos Lista de ObraDetailDTO a convertir.
     * @return Lista de ObraEntity convertida.
     * 
     */
    private List<ObraEntity> ObrasListDTO2Entity(List<ObraDetailDTO> dtos) {
        List<ObraEntity> list = new ArrayList<>();
        for (ObraDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de ObraDetailDTO asociadas a una
     * instancia de Compra
     *
     * @param comprasId Identificador de la instancia de Compra
     * @return Colección de instancias de ObraDetailDTO asociadas a la instancia
     * de Compra
     * 
     */
    @GET
    public List<ObraDetailDTO> listObras(@PathParam("comprasId") Long comprasId) {
        return ObrasListEntity2DTO(compraLogic.listObras(comprasId));
    }

    /**
     * Obtiene una instancia de Obra asociada a una instancia de Compra
     *
     * @param comprasId Identificador de la instancia de Compra
     * @param obrasId Identificador de la instancia de Obra
     * @return
     * @throws co.edu.uniandes.csw.Obrastore.exceptions.BusinessLogicException
     * 
     */
    @GET
    @Path("{obrasId: \\d+}")
    public ObraDetailDTO getObras(@PathParam("comprasId") Long comprasId, @PathParam("obrasId") Long obrasId) throws BusinessLogicException {
        return new ObraDetailDTO(compraLogic.getObra(comprasId, obrasId));
    }

    /**
     * Asocia un Obra existente a un Compra
     *
     * @param comprasId Identificador de la instancia de Compra
     * @param obrasId Identificador de la instancia de Obra
     * @return Instancia de ObraDetailDTO que fue asociada a Compra
     * 
     */
    @POST
    @Path("{obrasId: \\d+}")
    public ObraDetailDTO addObras(@PathParam("comprasId") Long comprasId, @PathParam("obrasId") Long obrasId) {
        return new ObraDetailDTO(compraLogic.addObra(obrasId,comprasId));
    }

    /**
     * Remplaza las instancias de Obra asociadas a una instancia de Compra
     *
     * @param comprasId Identificador de la instancia de Compra
     * @param obras Colección de instancias de ObraDTO a asociar a instancia de
     * Compra
     * @return Nueva colección de ObraDTO asociada a la instancia de Compra
     * 
     */
    @PUT
    public List<ObraDetailDTO> replaceObras(@PathParam("comprasId") Long comprasId, List<ObraDetailDTO> obras) {
        return ObrasListEntity2DTO(compraLogic.replaceObras(comprasId, ObrasListDTO2Entity(obras)));
    }

    /**
     * Desasocia un Obra existente de un Compra existente
     *
     * @param comprasId Identificador de la instancia de Compra
     * @param obrasId Identificador de la instancia de Obra
     * 
     */
    @DELETE
    @Path("{obrasId: \\d+}")
    public void removeObras(@PathParam("comprasId") Long comprasId, @PathParam("obrasId") Long obrasId) {
        compraLogic.removeObra(obrasId,comprasId);
    }
    
}
