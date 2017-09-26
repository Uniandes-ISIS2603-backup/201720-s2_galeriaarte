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
@Path("blogs")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BlogResource {
    
    @Inject
    BlogLogic blogLogic;
    
    /**
     * Convierte una lista de BlogEntity a una lista de BlogDTO.
     *
     * @param entityList Lista de BlogEntity a convertir.
     * @return Lista de BlogDTO convertida.
     * 
     */
    private List<BlogDTO> listEntity2DTO(List<BlogEntity> entityList) {
        List<BlogDTO> list = new ArrayList<>();
        for (BlogEntity entity : entityList) {
            list.add(new BlogDTO(entity));
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
    public List<BlogDTO> getBlogs() {
        return listEntity2DTO(blogLogic.getBlogs());
    }
    
    /**
     * Obtiene los datos de una instancia de Blog a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BlogDTO con los datos del Blog consultado
     * 
     */
    @GET
    @Path("{id: \\d+}")
    public BlogDTO getBlog(@PathParam("id") Long id) {
        BlogEntity entity = blogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El blog no existe", 404);
        }
        return new BlogDTO(entity);
    }
    
    /**
     * Se encarga de crear un Blog en la base de datos
     *
     * @param dto Objeto de BlogDTO con los datos nuevos
     * @return Objeto de BlogDTO con los datos nuevos y su ID
     * @throws co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     * 
     */
    @POST
    public BlogDTO createBlog(BlogDTO dto) throws BusinessLogicException {
        return new BlogDTO(blogLogic.createBlog(dto.toEntity()));
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
    public BlogDTO updateBlog(@PathParam("id") Long id, BlogDTO blog) throws BusinessLogicException {
        blog.setId(id);
        BlogEntity entity = blogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /blog/" + id + " no existe.", 404);
        }
        return new BlogDTO(blogLogic.updateBlog(id, blog.toEntity()));
    }
    
    /**
     * Elimina una instancia de Blog de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBlog(@PathParam("id") Long id) {
        BlogEntity entity = blogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El blog no existe", 404);
        }
        blogLogic.deleteBlog(id);
    }
}
