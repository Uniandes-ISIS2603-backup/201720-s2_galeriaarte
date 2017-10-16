/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.BlogPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.leon
 */
@Stateless
public class BlogLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
    
    @Inject
    private BlogPersistence persistence;
    
    @Inject
    private ArtistaLogic artistaLogic;
    
    /**
     * Obtiene la lista de los registros de Blog que pertenecen a un Artista.
     *
     * @param artistaid id del Artista el cual es padre de los Blogs.
     * @return Colección de objetos de BlogEntity.
     */
    public List<BlogEntity> getBlogs(Long artistaid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los blogs");
        ArtistaEntity artista = artistaLogic.getArtista(artistaid);
        if (artista.getBlogs() == null) {
            throw new BusinessLogicException("El artista que consulta aún no tiene blogs");
        }
        if (artista.getBlogs().isEmpty()) {
            throw new BusinessLogicException("El artista que consulta aún no tiene blogs");
        }
        return artista.getBlogs();
    }

    /**
     * Obtiene los datos de una instancia de Blog a partir de su ID.
     *
     * @param artistaid
     * @pre La existencia del elemento padre Artista se debe garantizar.
     * @param blogid Identificador del Blog a consultar
     * @return Instancia de BlogEntity con los datos del Blog consultado.
     * 
     */
    public BlogEntity getBlog(Long artistaid, Long blogid) {
        return persistence.find(artistaid, blogid);
    }

    /**
     * Se encarga de crear un Blog en la base de datos.
     *
     * @param entity Objeto de BlogEntity con los datos nuevos
     * @param artistaid id del Artista el cual sera padre del nuevo Blog.
     * @return Objeto de BlogEntity con los datos nuevos y su ID.
     * 
     */
    public BlogEntity createBlog(Long artistaid, BlogEntity entity) {
        LOGGER.info("Inicia proceso de crear blog");
        ArtistaEntity artista = artistaLogic.getArtista(artistaid);
        entity.setArtista(artista);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Blog.
     *
     * @param entity Instancia de ReviewEntity con los nuevos datos.
     * @param artistaid id del Artista el cual sera padre del Blog actualizado.
     * @return Instancia de BlogEntity con los datos actualizados.
     * 
     */
    public BlogEntity updateBlog(Long artistaid, BlogEntity entity) {
        LOGGER.info("Inicia proceso de actualizar blog");
        ArtistaEntity artista = artistaLogic.getArtista(artistaid);
        entity.setArtista(artista);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Blog de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param artistaid id del Artista el cual es padre del Blog.
     * 
     */
    public void deleteBlog(Long artistaid, Long id) {
        LOGGER.info("Inicia proceso de borrar blog");
        BlogEntity old = getBlog(artistaid, id);
        persistence.delete(old.getId());
    }
    
}
