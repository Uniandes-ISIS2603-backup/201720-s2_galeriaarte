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
@Path("compras")
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
            throw new WebApplicationException("El recurso /compras/" + id + " no existe.", 404);
        }
        return new CompraDetailDTO(entity);
    }

    /**
     
     * @param compra
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public CompraDetailDTO createCompra(CompraDetailDTO compra) throws BusinessLogicException {        
         return new CompraDetailDTO(compraLogic.createCompra(compra.toEntity()));
    }

    /**
     *
     
     * @param id
     * @param compra
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public CompraDetailDTO updateCompra(@PathParam("id") Long id, CompraDetailDTO compra) throws BusinessLogicException {
        CompraEntity entity = compra.toEntity();
        entity.setId(id);
        CompraEntity oldCompra = compraLogic.getCompra(id);
        if (oldCompra == null) {
            throw new WebApplicationException("El recurso /compras/" + id + " no existe.", 404);
        }
        entity.setPago(oldCompra.getPago());
        return new CompraDetailDTO(compraLogic.updateCompra(entity));
    }

    @DELETE
    @Path("{comprasId: \\d+}")
    public void deleteCompra(@PathParam("comprasId") Long id) throws BusinessLogicException {
        CompraEntity entity = compraLogic.getCompra(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compras/" + id + " no existe.", 404);
        }
        compraLogic.deleteCompra(id);
    }

  @Path("{comprasId: \\d+}/obras")
    public Class<CompraObrasResource> getCompraObrasResource(@PathParam("comprasId") Long comprasId) {
        CompraEntity entity = compraLogic.getCompra(comprasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compras/" + comprasId + " no existe.", 404);
        }
        return CompraObrasResource.class;
    }
    
    @Path("{idCompra: \\d+}/pago")
    public Class<PagoResource> getPagoResource(@PathParam("idCompra") Long comprasId) {
        CompraEntity entity = compraLogic.getCompra(comprasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compras/" + comprasId + "/pago no existe.", 404);
        }
        return PagoResource.class;
    }

    

    private List<CompraDetailDTO> listCompraEntity2DetailDTO(List<CompraEntity> entityList) {
        List<CompraDetailDTO> list = new ArrayList<>();
        for (CompraEntity entity : entityList) {
            list.add(new CompraDetailDTO(entity));
        }
        return list;
    }

    
}
