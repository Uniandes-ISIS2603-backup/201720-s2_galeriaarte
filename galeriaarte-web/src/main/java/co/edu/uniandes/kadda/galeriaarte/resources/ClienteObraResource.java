package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 *
 * @author ks.estupinan
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteObraResource {

    @Inject
    private ClienteLogic clienteLogic;

    /**
     * @param entityList
     * @return ObraDetailDTO
     */
    private List<ObraDetailDTO> obrasListEntity2DTO(List<ObraEntity> entityList) {
        List<ObraDetailDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) {
            list.add(new ObraDetailDTO(entity));
        }
        return list;
    }

    /**
     * @param dtos
     * @return ObraEntity
     */
    private List<ObraEntity> obrasListDTO2Entity(List<ObraDetailDTO> dtos) {
        List<ObraEntity> list = new ArrayList<>();
        for (ObraDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * @param clienteId
     * @return ObraDetailDTO
     */
    @GET
    public List<ObraDetailDTO> listObras(@PathParam("clienteId") Long clienteId) {
        return obrasListEntity2DTO(clienteLogic.listObras(clienteId));
    }

    /**
     * @param clienteId
     * @param obraId
     * @return ObraDetailDTO
     * @throws BusinessLogicException
     */
    @GET
    @Path("{obraId: \\d+}")
    public ObraDetailDTO getObras(@PathParam("clienteId") Long clienteId, @PathParam("obraId") Long obraId) throws BusinessLogicException {
        return new ObraDetailDTO(clienteLogic.getObra(clienteId, obraId));
    }

    /**
     * @param clienteId
     * @param obraId
     * @return ObraDetailDTO
     */
    @POST
    @Path("{obraId: \\d+}")
    public ObraDetailDTO addObra(@PathParam("clienteId") Long clienteId, @PathParam("obraId") Long obraId) {
        return new ObraDetailDTO(clienteLogic.addObra(obraId, clienteId));
    }

    /**
     * @param clienteId
     * @param obraId
     */
    @DELETE
    @Path("{obraId: \\d+}")
    public void removeObra(@PathParam("clienteId") Long clienteId, @PathParam("obraId") Long obraId) {
        clienteLogic.removeObra(clienteId, obraId);
    }
}
