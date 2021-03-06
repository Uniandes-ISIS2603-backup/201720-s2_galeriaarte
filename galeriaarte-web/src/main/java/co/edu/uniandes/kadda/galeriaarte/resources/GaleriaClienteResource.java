/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ClienteDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.ClienteDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    private GaleriaDetailDTO galeriaEntity2DetailDTO(GaleriaEntity galeriaEntity) {
        return new GaleriaDetailDTO(galeriaEntity);
    }

    /**
     * Convierte una lista de ClienteDTO a una lista de ClienteEntity.
     *
     * @param dtos Lista de ClienteDetailDTO a convertir.
     * @return Lista de ClienteEntity convertida.
     *
     */
    private List<ClienteEntity> clienteListDTO2Entity(List<ClienteDTO> dtos) {
        List<ClienteEntity> list = new ArrayList<>();
        for (ClienteDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Convierte una lista de ClienteEntity a una lista de ClienteDetailDTO.
     *
     * @param entityList Lista de ClienteEntity a convertir.
     * @return Lista de ClienteDetailDTO convertida.
     *
     */
    private List<ClienteDetailDTO> clienteListEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Asocia una Galeria existente a una Cliente
     *
     * @param clienteId Identificador de la instancia de cliente
     * @return Instancia de galeriaDetailDTO que fue asociada a cliente
     *
     */
    @POST
    @Path("{clienteId: \\d+}")
    public GaleriaDetailDTO addGaleria(@PathParam("clienteId") Long clienteId) {
        GaleriaEntity galeria = galeriaLogic.getGaleria();
        GaleriaDetailDTO resp = galeriaEntity2DetailDTO(galeria);
        galeriaLogic.addGaleriaACliente(clienteId);
        return resp;
    }

    @PUT
    public List<ClienteDetailDTO> replaceClientes(List<ClienteDTO> clientes) {
        return clienteListEntity2DTO(galeriaLogic.replaceClientes(clienteListDTO2Entity(clientes)));
    }
}
