/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CompraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.CompraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
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
import java.util.ArrayList;







/**
 *
 * @author ks.estupinan
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteCompraResource {

    @Inject
    private ClienteLogic clienteLogic;

    @Inject
    private CompraLogic compraLogic;

    /**
     *
     * @param entityList
     * @return
     */
    private List<CompraDetailDTO> comprasListEntity2DTO(List<CompraEntity> entityList) {
        List<CompraDetailDTO> list = new ArrayList<>();
        for (CompraEntity entity : entityList) {
            list.add(new CompraDetailDTO(entity));
        }
        return list;
    }

    /**
     *
     * @param dtos
     * @return
     */
    private List<CompraEntity> compraListDTO2Entity(List<CompraDetailDTO> dtos) {
        List<CompraEntity> list = new ArrayList<>();
        for (CompraDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     *
     * @param clienteId
     * @return
     */
    @GET
    public List<CompraDetailDTO> listCompras(@PathParam("clienteId") Long clienteId) {
        return comprasListEntity2DTO(clienteLogic.listCompras(clienteId));
    }

    /**
     *
     * @param clienteId
     * @param compraId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    @Path("{compraId: \\d+}")
    public CompraDetailDTO getCompras(@PathParam("clienteId") Long clienteId, @PathParam("compraId") Long compraId) throws BusinessLogicException {
        return new CompraDetailDTO(clienteLogic.getCompra(clienteId, compraId));
    }

    /**
     *
     * @param clienteId
     * @param compra
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public CompraDetailDTO addCompras(@PathParam("clienteId") Long clienteId, CompraDetailDTO compra) throws BusinessLogicException {
        CompraEntity c = compra.toEntity();
        return new CompraDetailDTO(clienteLogic.addCompra(clienteId, compraLogic.createCompra(c).getId()));
    }

}
