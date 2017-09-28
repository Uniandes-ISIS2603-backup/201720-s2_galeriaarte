/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ComentarioDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ComentarioLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
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
public class ClienteComentarioResource 
{
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private ComentarioLogic comentarioLogic;

    /**
     * 
     * @param entityList
     * @return 
     */
    private List<ComentarioDetailDTO> comentariosListEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * 
     * @param dtos
     * @return 
     */
    private List<ComentarioEntity> comentariosListDTO2Entity(List<ComentarioDetailDTO> dtos) {
        List<ComentarioEntity> list = new ArrayList<>();
        for (ComentarioDetailDTO dto : dtos) {
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
    public List<ComentarioDetailDTO> listComentarios(@PathParam("clienteId") Long clienteId) {
        return comentariosListEntity2DTO(clienteLogic.listComentarios(clienteId));
    }

    /**
     * 
     * @param clienteId
     * @param comentarioId
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{comentarioId: \\d+}")
    public ComentarioDetailDTO getComentarios(@PathParam("clienteId") Long clienteId, @PathParam("comentarioId") Long comentarioId) throws BusinessLogicException {
        return new ComentarioDetailDTO(clienteLogic.getComentario(clienteId, comentarioId));
    }

    /**
     * 
     * @param clienteId
     * @param comentarioId
     * @return 
     */
//    @POST
//    @Path("{comentarioId: \\d+}")
//    public ComentarioDetailDTO addComentarios(@PathParam("clienteId") Long clienteId, @PathParam("comentarioId") Long comentarioId) {
//        return new ComentarioDetailDTO(clienteLogic.addComentario(comentarioId,clienteId));
//    }
    /**
     * 
     * @param clienteId
     * @param comentario
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public ComentarioDetailDTO addComentarios(@PathParam("clienteId") Long clienteId, ComentarioDetailDTO comentario) throws BusinessLogicException {
        ComentarioEntity c = comentario.toEntity();
        return new ComentarioDetailDTO(clienteLogic.addComentario(clienteId,comentarioLogic.createComentario(c).getId()));
    }

    /**
     * 
     * @param clienteId
     * @param comentarios
     * @return 
     */
//    @PUT
//    public List<ComentarioDetailDTO> replaceComentarios(@PathParam("clienteId") Long clienteId, List<ComentarioDetailDTO> comentarios) {
//        return comentariosListEntity2DTO(clienteLogic.replaceComentarios(clienteId, comentariosListDTO2Entity(comentarios)));
//    }
    
    @PUT
    public ComentarioDetailDTO replaceComentarios(@PathParam("clienteId") Long clienteId, ComentarioDetailDTO comentario) throws BusinessLogicException {
        
        return new ComentarioDetailDTO(comentarioLogic.updateComentario(clienteId, clienteLogic.replaceComentario(clienteId, comentario.toEntity())));
        //return comentariosListEntity2DTO(clienteLogic.replaceComentarios(clienteId, comentariosListDTO2Entity(comentarios)));
    }

    /**
     * Desasocia un Book existente de un Editorial existente
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param booksId Identificador de la instancia de Book
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void removeComentario(@PathParam("clienteId") Long clienteId, @PathParam("comentarioId") Long comentarioId) {
        clienteLogic.removeComentario(comentarioId,clienteId);
    }
    
}
