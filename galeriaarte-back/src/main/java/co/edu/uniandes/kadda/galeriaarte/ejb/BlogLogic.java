/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

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
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public BlogEntity createBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un blog");
        
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un Blog con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear el blog

        LOGGER.info("Termina proceso de creación del blog");
        return persistence.create(entity);
    }
    
     /**
     * Borrar un blog
     *
     * @param id: id del blog a borrar
     */
    public void deleteBlog(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el blog con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el blog con id={0}", id);
    }
    
    /**
     *
     * Obtener todas los blogs existentes en la base de datos.
     *
     * @return una lista de blogs.
     */
    public List<BlogEntity> getBlogs() {
        LOGGER.info("Inicia proceso de consultar todos los blogs");
        List<BlogEntity> blogs = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los blogs");
        return blogs;
    }
    
    /**
     *
     * Obtener un blog por medio de su id.
     *
     * @param id: id del blog para ser buscada.
     * @return el blog solicitada por medio de su id.
     */
    public BlogEntity getBlog(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar blog con id={0}", id);
        BlogEntity blog = persistence.find(id);
        if (blog == null) {
            LOGGER.log(Level.SEVERE, "El blog con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar blog con id={0}", id);
        return blog;
    }
    
    /**
     *
     * Actualizar un blog.
     *
     * @param id: id del blog para buscarla en la base de datos.
     * @param entity: blog con los cambios para ser actualizada, por
     * ejemplo el contenido.
     * @return el blog con los cambios actualizados en la base de datos.
     */
    public BlogEntity updateBlog(Long id, BlogEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar blog con id={0}", id);
        
        BlogEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar blog con id={0}", entity.getId());
        return newEntity;
    }

    
}
