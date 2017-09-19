/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CompraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CompraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
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

/**
 *
 * @author ma.abril
 */
@Path("books")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompraResource {
    
    @Inject
    CompraLogic compraLogic;

    @GET
    public List<CompraDetailDTO> getCompras() throws BusinessLogicException {
        return listCompraEntity2DetailDTO(compraLogic.getCompras());
    }

    @GET
    @Path("{id: \\d+}")
    public CompraDetailDTO getCompra(@PathParam("id") Long id) throws BusinessLogicException {
        CompraEntity entity = compraLogic.getCompra(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Compras/" + id + " no existe.", 404);
        }
        return new CompraDetailDTO(entity);
    }

    /**
     * Ejemplo: { "description": "La comunicación en arquitectos de software.",
     * "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param Compra
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public CompraDetailDTO createCompra(CompraDetailDTO compra) throws BusinessLogicException {        
         return new CompraDetailDTO(compraLogic.createCompra(compra.toEntity()));
    }

    /**
     *
     * Ejemplo: { "description": "Las habilidades gerenciales en arquitectos de
     * software.", "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param id
     * @param Compra
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public CompraDetailDTO updateCompra(@PathParam("id") Long id, CompraDetailDTO compra) throws BusinessLogicException {
        compra.setId(id);
        CompraEntity entity = compraLogic.getCompra(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Compras/" + id + " no existe.", 404);
        }
        return new CompraDetailDTO(compraLogic.updateCompra(id, compra.toEntity()));
    }

    @DELETE
    @Path("{ComprasId: \\d+}")
    public void deleteCompra(@PathParam("ComprasId") Long id) throws BusinessLogicException {
        CompraEntity entity = compraLogic.getCompra(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Compras/" + id + " no existe.", 404);
        }
        compraLogic.deleteCompra(id);
    }

    @Path("{idCompra: \\d+}/obras")
    public Class<ObraResource> getObraResource(@PathParam("idCompra") Long comprasId) {
        CompraEntity entity = compraLogic.getCompra(comprasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Compras/" + comprasId + "/obras no existe.", 404);
        }
        return ObraResource.class;
    }

    

    private List<CompraDetailDTO> listCompraEntity2DetailDTO(List<CompraEntity> entityList) {
        List<CompraDetailDTO> list = new ArrayList<>();
        for (CompraEntity entity : entityList) {
            list.add(new CompraDetailDTO(entity));
        }
        return list;
    }

    
}
