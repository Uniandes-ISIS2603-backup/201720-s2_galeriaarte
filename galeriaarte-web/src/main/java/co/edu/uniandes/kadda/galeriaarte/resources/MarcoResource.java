/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.MarcoDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.MarcoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
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
 * @author af.leon
 */
@Path("marcos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MarcoResource {
    
    @Inject
    MarcoLogic marcoLogic;
    
    /**
     * Convierte una lista de MarcoEntity a una lista de MarcoDetailDTO.
     *
     * @param entityList Lista de MarcoEntity a convertir.
     * @return Lista de MarcoDetailDTO convertida.
     * 
     */
    private List<MarcoDetailDTO> listEntity2DetailDTO(List<MarcoEntity> entityList) {
        List<MarcoDetailDTO> list = new ArrayList<>();
        for (MarcoEntity entity : entityList) {
            list.add(new MarcoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene la lista de los registros de Marcos
     *
     * @return Colección de objetos de MarcoDetailDTO
     * 
     */
    @GET
    public List<MarcoDetailDTO> getMarcos() {
        return listEntity2DetailDTO(marcoLogic.getMarcos());
    }
    
    /**
     * Obtiene los datos de una instancia de Marco a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de MarcoDTO con los datos del Marco consultado
     * 
     */
    @GET
    @Path("{id: \\d+}")
    public MarcoDetailDTO getMarco(@PathParam("id") Long id) {
        MarcoEntity entity = marcoLogic.getMarco(id);
        if (entity == null) {
            throw new WebApplicationException("El marco no existe", 404);
        }
        return new MarcoDetailDTO(entity);
    }
    
    /**
     * Se encarga de crear un Marco en la base de datos
     *
     * @param dto Objeto de MarcoDetailDTO con los datos nuevos
     * @return Objeto de MarcoDetailDTO con los datos nuevos y su ID
     * @throws co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     * 
     */
    @POST
    public MarcoDetailDTO createMarco(MarcoDetailDTO dto) throws BusinessLogicException {
        return new MarcoDetailDTO(marcoLogic.createMarco(dto.toEntity()));
    }
    
    /**
     * Actualiza la información de una instancia de Marco
     *
     * @param id Identificador de la instancia de Marco a modificar
     * @param marco
     * @return Instancia de MarcoDetailDTO con los datos actualizados
     * @throws co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     * 
     */
    @PUT
    @Path("{id: \\d+}")
    public MarcoDetailDTO updateMarco(@PathParam("id") Long id, MarcoDetailDTO marco) throws BusinessLogicException {
        marco.setId(id);
        MarcoEntity entity = marcoLogic.getMarco(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /marco/" + id + " no existe.", 404);
        }
        return new MarcoDetailDTO(marcoLogic.updateMarco(id, marco.toEntity()));
    }
    
    /**
     * Elimina una instancia de Marco de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMarco(@PathParam("id") Long id) {
        MarcoEntity entity = marcoLogic.getMarco(id);
        if (entity == null) {
            throw new WebApplicationException("El marco no existe", 404);
        }
        marcoLogic.deleteMarco(id);
    }
}
