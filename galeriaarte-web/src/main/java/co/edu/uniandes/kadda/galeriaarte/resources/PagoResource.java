/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.PagoDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.PagoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ma.abril
 */
@Produces("application/json")
@Consumes("application/json")
public class PagoResource {
    
    

    @Inject
    PagoLogic pagoLogic;

    @GET
    public List<PagoDTO> getPagos() throws BusinessLogicException {
        return listEntity2DTO(pagoLogic.getPagos());
    }

    @GET
    @Path("{id: \\d+}")
    public PagoDTO getPago(@PathParam("idCompra") Long idCompra, @PathParam("id") Long id) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getPago(idCompra, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compras/" + idCompra + "/pago/" + id + " no existe.", 404);
        }
        return new PagoDTO(entity);
    }

    @POST
    public PagoDTO createPago(@PathParam("idCompra") Long idCompra, PagoDTO pago) throws BusinessLogicException {
        return new PagoDTO(pagoLogic.createPago(idCompra, pago.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public PagoDTO updatePago(@PathParam("idCompra") Long idCompra, @PathParam("id") Long id, PagoDTO pago) throws BusinessLogicException {
        pago.setId(id);
        PagoEntity entity = pagoLogic.getPago(idCompra, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compra/" + idCompra + "/pago/" + id + " no existe.", 404);
        }
        return new PagoDTO(pagoLogic.updatePago(idCompra, pago.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("idCompra") Long idCompra, @PathParam("id") Long id) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getPago(idCompra, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compra/" + idCompra + "/pago/" + id + " no existe.", 404);
        }
        pagoLogic.deletePago(idCompra, id);
    }

    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
    
}
