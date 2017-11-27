package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ClienteDTO;
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
     *
     * @return ClienteDetailDTO
     * @throws BusinessLogicException
     */
    @GET
    public List<ClienteDetailDTO> getClientes() throws BusinessLogicException {
        return listEntity2DTO(clienteLogic.getClientes());
    }

    /**
     *
     * @param id
     * @return ClienteDetailDTO
     * @throws BusinessLogicException
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
     * @return ClienteDetailDTO
     * @throws BusinessLogicException
     */
    @POST
    public ClienteDetailDTO createCliente(ClienteDetailDTO dto) throws BusinessLogicException {
        return new ClienteDetailDTO(clienteLogic.createCliente(dto.toEntity()));
    }

    /**
     *
     * @param id
     * @param dto
     * @return ClienteDetailDTO
     * @throws BusinessLogicException
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
     *
     * @param id
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

    /**
     *
     * @param entityList
     * @return ClienteDetailDTO
     */
    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    /**
     *
     * @param dtoList
     * @return ClienteEntity
     */
    public ArrayList<ClienteEntity> listDTO2Entity(List<ClienteDTO> dtoList) {
        ArrayList<ClienteEntity> lista = new ArrayList<ClienteEntity>();
        for (ClienteDTO dto : dtoList) {
            ClienteEntity e = new ClienteEntity();
            e.setId(dto.getId());
            e.setName(dto.getName());

            lista.add(e);
        }
        return lista;
    }

    /**
     *
     * @param clienteId
     * @return ClienteComentarioResource
     */
    @Path("{clienteId: \\d+}/comentarios")
    public Class<ClienteComentarioResource> getClienteComentarioResource(@PathParam("clienteId") Long clienteId) {
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return ClienteComentarioResource.class;
    }

    /**
     *
     * @param clienteId
     * @return ClienteObraResource
     */
    @Path("{clienteId: \\d+}/obras")
    public Class<ClienteObraResource> getClienteObraResource(@PathParam("clienteId") Long clienteId) {
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return ClienteObraResource.class;
    }

    /**
     *
     * @param clienteId
     * @return ClienteCompraResource
     */
    @Path("{clienteId: \\d+}/compras")
    public Class<ClienteCompraResource> getClienteCompraResource(@PathParam("clienteId") Long clienteId) {
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return ClienteCompraResource.class;
    }
}
