/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ClientePersistence;
import java.util.Iterator;
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
    
    @Inject
    private ObraLogic obraLogic;

    /**
     *
     * @param entity
     * @return
     */
    public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException 
    {
        if(persistence.find(entity.getId())!=null){
            throw new BusinessLogicException("Ya existe un cliente con el id \"" + entity.getId() + "\"");
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
     * @return
     */
    public List<ObraEntity> listObras(Long clienteId) {
        return getCliente(clienteId).getObra();
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
        entity.setComentario(comentario);
        return comentarioLogic.getComentario(comentarioId);
    }
    
    /**
     *
     * @param clienteId
     * @param obraId
     * @return
     */
    public ObraEntity addObra(Long clienteId, Long obraId) {
        ClienteEntity entity = getCliente(clienteId);
        ObraEntity obra = obraLogic.findObra(obraId);
        obra.setCliente(entity);
        return obraLogic.findObra(obraId);
    }

    /**
     * 
     * @param clienteId
     * @return 
     */
    public List<ComentarioEntity> getComentarios(Long clienteId) {
        return getCliente(clienteId).getComentarios();
    }
    /**
     * 
     * @param clienteId
     * @return 
     */
    public List<ObraEntity> getObras(Long clienteId) {
        return getCliente(clienteId).getObra();
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
     * @param obraId
     * @return
     * @throws BusinessLogicException 
     */
    public ObraEntity getObra(Long clienteId, Long obraId) throws BusinessLogicException {
        List<ObraEntity> list = getCliente(clienteId).getObra();
        ObraEntity obraEntity = obraLogic.findObra(obraId);
        int index = list.indexOf(obraEntity);
        if (index >= 0) {
            return list.get(index);
        }
        throw new BusinessLogicException("La obra no está asociado al cliente");
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
    
        public ComentarioEntity replaceComentario(Long clienteId, ComentarioEntity entidad) {
        ClienteEntity cliente = getCliente(clienteId);
        List<ComentarioEntity> comentarioList = comentarioLogic.getComentarios();
        
        ComentarioEntity actual = null;
        Iterator<ComentarioEntity> it = comentarioList.iterator();
        while(it.hasNext())
        {
            actual = it.next();
            if(actual.getId().equals(entidad.getId()))
            {
                actual.setName(entidad.getName());
                break;
            }
        }
        return actual;
    }
    
    /**
     * 
     * @param clienteId
     * @param list
     * @return 
     */
    public List<ObraEntity> replaceObras(Long clienteId, List<ObraEntity> list) {
        ClienteEntity cliente = getCliente(clienteId);
        List<ObraEntity> obraList = obraLogic.getObras();
        for (ObraEntity obra : obraList) {
            if (list.contains(obra)) {
                obra.setCliente(cliente);
            } else if (obra.getCliente() != null && obra.getCliente().equals(cliente)) {
                obra.setCliente(null);
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
    
    public void removeObra(Long clienteId, Long obraId) {
        ClienteEntity clienteEntity = getCliente(clienteId);
        ObraEntity obra = obraLogic.findObra(obraId);
        obra.setCliente(null);
        clienteEntity.getObra().remove(obra);
    }
}
