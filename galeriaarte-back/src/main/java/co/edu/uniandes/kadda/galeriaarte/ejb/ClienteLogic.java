/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ClientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ks.estupinan
 */
@Stateless
public class ClienteLogic {

    @Inject
    private ClientePersistence persistence;

    @Inject
    private ComentarioLogic comentarioLogic;

    /**
     *
     * @param entity
     * @return
     */
    public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Cliente con el id \"" + entity.getId() + "\"");
        }
        return persistence.create(entity);
    }

    /**
     *
     * @return
     */
    public List<ClienteEntity> getClientes() {
        return persistence.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public ClienteEntity getCliente(Long id) {
        ClienteEntity cliente = persistence.find(id);
        if (cliente == null) {
        }
        return cliente;
    }

    /**
     *
     * @param entity
     * @return
     */
    public ClienteEntity updateCliente(ClienteEntity entity) {
        return persistence.update(entity);
    }

    /**
     *
     * @param id
     */
    public void deleteCliente(Long id) {
        persistence.delete(id);
    }

    /**
     *
     * @param clienteId
     * @return
     */
    public List<ComentarioEntity> listComentarios(Long clienteId) {
        return getCliente(clienteId).getComentarios();
    }

    /**
     *
     * @param clienteId
     * @param comentarioId
     * @return
     */
    public ComentarioEntity addComentario(Long clienteId, Long comentarioId) {
        ClienteEntity entity = getCliente(clienteId);
        ComentarioEntity comentario = comentarioLogic.getComentario(comentarioId);
        comentario.setClienteComentario(entity);
        return comentarioLogic.getComentario(comentarioId);
    }

    public List<ComentarioEntity> getComentarios(Long clienteId) {
        return getCliente(clienteId).getComentarios();
    }

    /**
     *
     * @param clienteId
     * @param comentarioId
     * @return
     */
    public ComentarioEntity getComentario(Long clienteId, Long comentarioId) throws BusinessLogicException {
        List<ComentarioEntity> list = getCliente(clienteId).getComentarios();
        ComentarioEntity comentarioEntity = comentarioLogic.getComentario(comentarioId);
        int index = list.indexOf(comentarioEntity);
        if (index >= 0) {
            return list.get(index);
        }
        throw new BusinessLogicException("El comentario no está asociado al cliente");
    }

    /**
     *
     * @param clienteId
     * @param list
     * @return
     */
    public List<ComentarioEntity> replaceComentarios(Long clienteId, List<ComentarioEntity> list) {
        ClienteEntity cliente = getCliente(clienteId);
        List<ComentarioEntity> comentarioList = comentarioLogic.getComentarios();
        for (ComentarioEntity comentario : comentarioList) {
            if (list.contains(comentario)) {
                comentario.setClienteComentario(cliente);
            } else if (comentario.getClienteComentario() != null && comentario.getClienteComentario().equals(cliente)) {
                comentario.setClienteComentario(null);
            }
        }
        return list;
    }

    /**
     *
     * @param clienteId
     * @param comentarioId
     */
    public void removeComentario(Long clienteId, Long comentarioId) {
        ClienteEntity clienteEntity = getCliente(clienteId);
        ComentarioEntity comentario = comentarioLogic.getComentario(comentarioId);
        comentario.setClienteComentario(null);
        clienteEntity.getComentarios().remove(comentario);
    }
}
