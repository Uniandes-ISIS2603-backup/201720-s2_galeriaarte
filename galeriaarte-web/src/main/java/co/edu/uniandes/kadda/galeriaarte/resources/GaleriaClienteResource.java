/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Perilla
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GaleriaClienteResource {
    
    @Inject 
    private GaleriaLogic galeriaLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos GaleriaEntity a una lista de
     * objetos GaleriaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Galerias de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Galerias en forma DTO (json)
     */
    private List<GaleriaDetailDTO> listGaleriaEntity2DetailDTO(List<GaleriaEntity> entityList) {
        List<GaleriaDetailDTO> list = new ArrayList<>();
        for (GaleriaEntity entity : entityList) {
            list.add(new GaleriaDetailDTO(entity));
        }
        return list;
    }
    
    private GaleriaDetailDTO galeriaEntity2DetailDTO(GaleriaEntity galeriaEntity)
    {
        GaleriaDetailDTO resp = new GaleriaDetailDTO(galeriaEntity);
        return resp;
    }
    
    
     /**
     * Asocia una Galeria existente a una Cliente
     *
     * @param clienteId Identificador de la instancia de cliente
     * @param galeriaId Identificador de la instancia de galería
     * @return Instancia de galeriaDetailDTO que fue asociada a cliente
     * 
     */
    @POST
    @Path("{clienteId: \\d+}")
    public GaleriaDetailDTO addGaleria(@PathParam("clienteId") Long clienteId)
    {
        GaleriaEntity galeria = galeriaLogic.getGaleria();
        GaleriaDetailDTO resp = galeriaEntity2DetailDTO(galeria);
        galeriaLogic.addGaleriaACliente(clienteId);  
        return resp;
    }
}

