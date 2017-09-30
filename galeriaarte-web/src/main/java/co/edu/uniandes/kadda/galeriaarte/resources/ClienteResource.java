/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ClienteDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ks.estupinan
 */
@Path("cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteResource {

    @Inject
    private ClienteLogic clienteLogic;

    /**
     * Obtiene la lista de los registros de Author
     *
     * @return Colección de objetos de AuthorDetailDTO
     *
     */
    @GET
    public List<ClienteDetailDTO> getClientes() throws BusinessLogicException {
        return listEntity2DTO(clienteLogic.getClientes());
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorDetailDTO con los datos del Author consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) throws BusinessLogicException {
        ClienteEntity entity = clienteLogic.getCliente(id);
        if (entity == null) {
            throw new WebApplicationException("El Cliente no existe", 404);
        }
        return new ClienteDetailDTO(entity);
    }

    /**
     *
     * @param dto
     * @return
     */
    @POST
    public ClienteDetailDTO createCliente(ClienteDetailDTO dto) throws BusinessLogicException {
        return new ClienteDetailDTO(clienteLogic.createCliente(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Author
     *
     * @param id Identificador de la instancia de Author a modificar
     * @param dto Instancia de AuthorDetailDTO con los nuevos datos
     * @return Instancia de AuthorDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id, ClienteDetailDTO dto) throws BusinessLogicException {
        ClienteEntity entity = dto.toEntity();
        entity.setId(id);
        ClienteEntity oldEntity = clienteLogic.getCliente(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El Cliente no existe", 404);
        }
        entity.setComentarios(oldEntity.getComentarios());
        return new ClienteDetailDTO(clienteLogic.updateCliente(entity));
    }

    /**
     * Elimina una instancia de Author de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCliente(@PathParam("id") Long id) {
        ClienteEntity entity = clienteLogic.getCliente(id);
        if (entity == null) {
            throw new WebApplicationException("El Cliente no existe", 404);
        }
        clienteLogic.deleteCliente(id);
    }

    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    @Path("{clienteId: \\d+}/comentarios")
    public Class<ClienteComentarioResource> getClienteComentarioResource(@PathParam("clienteId") Long clienteId) {
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return ClienteComentarioResource.class;
    }
    
    @Path("{clienteId: \\d+}/obras")
    public Class<ClienteObraResource> getClienteObraResource(@PathParam("clienteId") Long clienteId) {
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return ClienteObraResource.class;
    }
}
