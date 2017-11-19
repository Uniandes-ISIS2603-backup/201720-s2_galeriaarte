/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ComentarioDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ComentarioLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ks.estupinan
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {

    @Inject
    ComentarioLogic comentarioLogic;

    /**
     *
     * @return @throws BusinessLogicException
     */
    @GET
    public List<ComentarioDetailDTO> getComentarios() throws BusinessLogicException {
        return listComentarioEntity2DetailDTO(comentarioLogic.getComentarios());
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public ComentarioDetailDTO getComentario(@PathParam("id") Long id) throws BusinessLogicException {
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /comentarios/" + id + " no existe.", 404);
        }
        return new ComentarioDetailDTO(entity);
    }

    /**
     *
     * @param comentario
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public ComentarioDetailDTO createComentario(ComentarioDetailDTO comentario) throws BusinessLogicException {
        return new ComentarioDetailDTO(comentarioLogic.createComentario(comentario.toEntity()));
    }

    /**
     *
     * @param id
     * @param comentario
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public ComentarioDetailDTO updateBook(@PathParam("id") Long id, ComentarioDetailDTO comentario) throws BusinessLogicException {
        comentario.setId(id);
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Comentarios/" + id + " no existe.", 404);
        }
        ComentarioEntity com = comentario.toEntity();
        com.setClienteComentario(entity.getClienteComentario());
        return new ComentarioDetailDTO(comentarioLogic.updateComentario(id, com));
    }

    @DELETE
    @Path("{comentariosId: \\d+}")
    public void deleteComentario(@PathParam("comentariosId") Long id) throws BusinessLogicException {
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Comentarios/" + id + " no existe.", 404);
        }
        comentarioLogic.deleteComentario(id);
    }

    private List<ComentarioDetailDTO> listComentarioEntity2DetailDTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDetailDTO(entity));
        }
        return list;
    }

}
