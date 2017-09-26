/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.MarcoDTO;
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
     * Convierte una lista de MarcoEntity a una lista de MarcoDTO.
     *
     * @param entityList Lista de MarcoEntity a convertir.
     * @return Lista de MarcoDTO convertida.
     * 
     */
    private List<MarcoDTO> listEntity2DTO(List<MarcoEntity> entityList) {
        List<MarcoDTO> list = new ArrayList<>();
        for (MarcoEntity entity : entityList) {
            list.add(new MarcoDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene la lista de los registros de Blogs
     *
     * @return Colección de objetos de BlogDTO
     * 
     */
    @GET
    public List<MarcoDTO> getMarcos() {
        return listEntity2DTO(marcoLogic.getMarcos());
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
    public MarcoDTO getMarco(@PathParam("id") Long id) {
        MarcoEntity entity = marcoLogic.getMarco(id);
        if (entity == null) {
            throw new WebApplicationException("El marco no existe", 404);
        }
        return new MarcoDTO(entity);
    }
    
    /**
     * Se encarga de crear un Marco en la base de datos
     *
     * @param dto Objeto de MarcoDTO con los datos nuevos
     * @return Objeto de MarcoTO con los datos nuevos y su ID
     * @throws co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     * 
     */
    @POST
    public MarcoDTO createMarco(MarcoDTO dto) throws BusinessLogicException {
        return new MarcoDTO(marcoLogic.createMarco(dto.toEntity()));
    }
    
    /**
     * Actualiza la información de una instancia de Blog
     *
     * @param id Identificador de la instancia de Blog a modificar
     * @param blog
     * @return Instancia de BlogDTO con los datos actualizados
     * @throws co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     * 
     */
    @PUT
    @Path("{id: \\d+}")
    public MarcoDTO updateMarco(@PathParam("id") Long id, MarcoDTO marco) throws BusinessLogicException {
        marco.setId(id);
        MarcoEntity entity = marcoLogic.getMarco(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /marco/" + id + " no existe.", 404);
        }
        return new MarcoDTO(marcoLogic.updateMarco(id, marco.toEntity()));
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
