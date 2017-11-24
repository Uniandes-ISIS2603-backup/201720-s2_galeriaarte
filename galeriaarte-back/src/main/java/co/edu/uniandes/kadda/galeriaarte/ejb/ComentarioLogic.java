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

    /**
     *
     * @return ComentarioEntity
     */
    public List<ComentarioEntity> getComentarios() {
        List<ComentarioEntity> comentarios = persistence.findAll();
        return comentarios;
    }

    /**
     *
     * @param id
     * @return ComentarioEntity
     */
    public ComentarioEntity getComentario(Long id) {
        ComentarioEntity comentario = persistence.find(id);
        if (comentario == null) {
        }
        return comentario;
    }

    /**
     *
     * @param entity
     * @return ComentarioEntity
     * @throws BusinessLogicException
     */
    public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException {
        persistence.create(entity);
        return entity;
    }

    /**
     *
     * @param id
     * @param entity
     * @return ComentarioEntity
     * @throws BusinessLogicException
     */
    public ComentarioEntity updateComentario(Long id, ComentarioEntity entity) throws BusinessLogicException {
        ComentarioEntity newEntity = persistence.update(entity);
        return newEntity;
    }

    /**
     *
     * @param entity
     * @return ComentarioEntity
     * @throws BusinessLogicException
     */
    public ComentarioEntity updateComentario(ComentarioEntity entity) throws BusinessLogicException {
        ComentarioEntity newEntity = persistence.update(entity);
        return newEntity;
    }

    /**
     *
     * @param id
     */
    public void deleteComentario(Long id) {
        persistence.delete(id);
    }

    /**
     *
     * @param comentarioId
     * @return
     */
    public ClienteEntity getCliente(Long comentarioId) {
        return getComentario(comentarioId).getClienteComentario();
    }

    /**
     *
     * @param comentarioId
     * @param clienteId
     * @return ClienteEntity
     */
    public ClienteEntity addCliente(Long comentarioId, Long clienteId) {
        ComentarioEntity comentarioEntity = getComentario(comentarioId);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteId);
        comentarioEntity.setClienteComentario(clienteEntity);
        return getCliente(comentarioId);
    }

    /**
     *
     * @param comentarioId
     * @param entidad
     * @return ClienteEntity
     */
    public ClienteEntity replaceCliente(Long comentarioId, ClienteEntity entidad) {
        ComentarioEntity comentarioEntity = getComentario(comentarioId);
        comentarioEntity.setClienteComentario(entidad);
        return comentarioEntity.getClienteComentario();
    }

    /**
     *
     * @param comentarioId
     * @param clienteId
     */
    public void removeCliente(Long comentarioId, Long clienteId) {
        ComentarioEntity entity = getComentario(comentarioId);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteId);
        entity.setClienteComentario(null);
    }
}
