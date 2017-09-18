/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Perilla
 */
@Stateless
public class CatalogoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(GaleriaPersistence.class.getName());

    @PersistenceContext(unitName = "galeriadeartePU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Blog que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CatalogoEntity create(CatalogoEntity entity) {
        LOGGER.info("Creando un catalogo nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Galeria en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un catalogo nuevo");
        return entity;
    }
    
    /**
     * Actualiza un catalogo.
     *
     * @param entity: el catalogo que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un catalogo con los cambios aplicados.
     */
    public CatalogoEntity update(CatalogoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Catalogo con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Galeria con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un catalogo de la base de datos recibiendo como argumento el id
     * de el Catalogo
     *
     * @param id: id correspondiente al catalogo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Catalogo con id={0}", id);
        
        CatalogoEntity entity = em.find(CatalogoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from GaleriaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
    
    /**
     * Busca si hay algun catalogo con el id que se envía de argumento
     *
     * @param id: id correspondiente al catalogo buscado.
     * @return un catalogo.
     */
    public CatalogoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Catalogo con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from GaleriaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(CatalogoEntity.class, id);
    }
    
    /**
     * Devuelve todas los Catalogos de la base de datos.
     *
     * @return una lista con todas los Catalogos que encuentre en la base de
     * datos, "select u from GaleriaEntity u" es como un "select * from
     * BlogEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<CatalogoEntity> findAll() {
        LOGGER.info("Consultando todas los Catalogos");
        // Se crea un query para buscar todas los Catalogos en la base de datos.
        TypedQuery query = em.createQuery("select u from CatalogoEntity u", CatalogoEntity.class);
        
        return query.getResultList();
    }
}
