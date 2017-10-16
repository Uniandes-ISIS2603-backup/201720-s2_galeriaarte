/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;


import co.edu.uniandes.kadda.galeriaarte.dtos.BlogDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.BlogLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
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
 * Clase que implementa el recurso REST correspondiente a "blogs".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "blogs". Al ejecutar la aplicación, el recurso
 * será accesible a través de la ruta "/api/artistas/idArtista/blogs"
 * 
 * 
 * 
 * @author af.leon
 */
@Produces("application/json")
@Consumes("application/json")
public class BlogResource {
    
    @Inject
    BlogLogic blogLogic;

    @GET
    public List<BlogDTO> getBlogs(@PathParam("idArtista") Long idArtista) throws BusinessLogicException {
        return listEntity2DTO(blogLogic.getBlogs(idArtista));
    }

    @GET
    @Path("{id: \\d+}")
    public BlogDTO getBlog(@PathParam("idArtista") Long idArtista, @PathParam("id") Long id) throws BusinessLogicException {
        BlogEntity entity = blogLogic.getBlog(idArtista, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + idArtista + "/blogs/" + id + " no existe.", 404);
        }
        return new BlogDTO(entity);
    }

    @POST
    public BlogDTO createBlog(@PathParam("idArtista") Long idArtista, BlogDTO blog) throws BusinessLogicException {
        return new BlogDTO(blogLogic.createBlog(idArtista, blog.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public BlogDTO updateBlog(@PathParam("idArtista") Long idArtista, @PathParam("id") Long id, BlogDTO blog) throws BusinessLogicException {
        blog.setId(id);
        BlogEntity entity = blogLogic.getBlog(idArtista, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + idArtista + "/blogs/" + id + " no existe.", 404);
        }
        return new BlogDTO(blogLogic.updateBlog(idArtista, blog.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteBlog(@PathParam("idArtista") Long idArtista, @PathParam("id") Long id) throws BusinessLogicException {
        BlogEntity entity = blogLogic.getBlog(idArtista, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + idArtista + "/blogs/" + id + " no existe.", 404);
        }
        blogLogic.deleteBlog(idArtista, id);
    }

    private List<BlogDTO> listEntity2DTO(List<BlogEntity> entityList) {
        List<BlogDTO> list = new ArrayList<>();
        for (BlogEntity entity : entityList) {
            list.add(new BlogDTO(entity));
        }
        return list;
    }
    
}
