/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ComentarioPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ks.estupinan
 */
public class ComentarioLogic {

    @Inject
    private ComentarioPersistence persistence;

    public List<ComentarioEntity> getComentarios() {
        List<ComentarioEntity> comentarios = persistence.findAll();
        return comentarios;
    }

    public ComentarioEntity getComentario(Long id) {
        ComentarioEntity comentario = persistence.find(id);
        if (comentario == null) {
        }
        return comentario;
    }

    public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException {
        persistence.create(entity);
        return entity;
    }

    public ComentarioEntity updateComentario(Long id, ComentarioEntity entity) throws BusinessLogicException {
        ComentarioEntity newEntity = persistence.update(entity);
        return newEntity;
    }

    public void deleteComentario(Long id) {
        persistence.delete(id);
    }

    /**
     * Obtiene una colección de instancias de AuthorEntity asociadas a una
     * instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @return Colección de instancias de AuthorEntity asociadas a la instancia
     * de Book
     *
     */
    public ClienteEntity getCliente(Long comentarioId) {
        return getComentario(comentarioId).getClienteComentario();
    }
    
    /**
     * Asocia un Author existente a un Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     * @return Instancia de AuthorEntity que fue asociada a Book
     *
     */
    public ClienteEntity addCliente(Long comentarioId, Long clienteId) {
        ComentarioEntity comentarioEntity = getComentario(comentarioId);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteId);
        comentarioEntity.setClienteComentario(clienteEntity);
        return getCliente(comentarioId);
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param list Colección de instancias de AuthorEntity a asociar a instancia
     * de Book
     * @return Nueva colección de AuthorEntity asociada a la instancia de Book
     *
     */
    public ClienteEntity replaceCliente(Long comentarioId, ClienteEntity entidad) {
        ComentarioEntity comentarioEntity = getComentario(comentarioId);
        comentarioEntity.setClienteComentario(entidad);
        return comentarioEntity.getClienteComentario();
    }

    /**
     * Desasocia un Author existente de un Book existente
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     *
     */
    public void removeCliente(Long comentarioId, Long clienteId) {
        ComentarioEntity entity = getComentario(comentarioId);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteId);
        entity.setClienteComentario(null);
    }    
}
