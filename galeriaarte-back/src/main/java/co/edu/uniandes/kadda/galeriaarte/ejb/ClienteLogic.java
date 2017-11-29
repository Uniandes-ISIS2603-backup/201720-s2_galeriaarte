package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
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

    @Inject
    private CompraLogic compraLogic;

    /**
     * @param entity
     * @return ClienteEntity
     */
    public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un cliente con el id \"" + entity.getId() + "\"");
        }
        return persistence.create(entity);
    }

    /**
     * @return ClienteEntity
     */
    public List<ClienteEntity> getClientes() {
        return persistence.findAll();
    }

    /**
     * @param id
     * @return ClienteEntity
     */
    public ClienteEntity getCliente(Long id) {
        return persistence.find(id);
    }

    /**
     * @param entity
     * @return ClienteEntity
     */
    public ClienteEntity updateCliente(ClienteEntity entity) {
        return persistence.update(entity);
    }

    /**
     * @param id
     */
    public void deleteCliente(Long id) {
        persistence.delete(id);
    }

    /**
     * @param clienteId
     * @return ComentarioEntity
     */
    public List<ComentarioEntity> listComentarios(Long clienteId) {
        return getCliente(clienteId).getComentarios();
    }

    /**
     * @param clienteId
     * @return ObraEntity
     */
    public List<ObraEntity> listObras(Long clienteId) {
        return getCliente(clienteId).getObra();
    }

    /**
     * @param clienteId
     * @return CompraEntity
     */
    public List<CompraEntity> listCompras(Long clienteId) {
        return getCliente(clienteId).getCompra();
    }

    /**
     * @param clienteId
     * @param comentarioId
     * @return ComentarioEntity
     */
    public ComentarioEntity addComentario(Long clienteId, Long comentarioId) {
        ClienteEntity entity = getCliente(clienteId);
        ComentarioEntity comentario = comentarioLogic.getComentario(comentarioId);
        comentario.setClienteComentario(entity);
        entity.setComentario(comentario);
        return comentarioLogic.getComentario(comentarioId);
    }

    /**
     * @param clienteId
     * @param obraId
     * @return ObraEntity
     */
    public ObraEntity addObra(Long clienteId, Long obraId) {
        ClienteEntity entity = getCliente(clienteId);
        ObraEntity obra = obraLogic.findObra(obraId);
        entity.setObra(obra);
        return obraLogic.findObra(obraId);
    }

    /**
     * @param clienteId
     * @param compraId
     * @return CompraEntity
     */
    public CompraEntity addCompra(Long clienteId, Long compraId) {
        ClienteEntity entity = getCliente(clienteId);
        CompraEntity compra = compraLogic.getCompra(compraId);
        compra.setCliente(entity);
        entity.setCompra(compra);
        return compraLogic.getCompra(compraId);
    }

    /**
     * @param clienteId
     * @return ComentarioEntity
     */
    public List<ComentarioEntity> getComentarios(Long clienteId) {
        return getCliente(clienteId).getComentarios();
    }

    /**
     * @param clienteId
     * @return ObraEntity
     */
    public List<ObraEntity> getObras(Long clienteId) {
        return getCliente(clienteId).getObra();
    }

    /**
     * @param clienteId
     * @return CompraEntity
     */
    public List<CompraEntity> getCompras(Long clienteId) {
        return getCliente(clienteId).getCompra();
    }

    /**
     * @param clienteId
     * @param comentarioId
     * @return ComentarioEntity
     * @throws BusinessLogicException
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
     * @return ObraEntity
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
     * @param clienteId
     * @param compraId
     * @return CompraEntity
     * @throws BusinessLogicException
     */
    public CompraEntity getCompra(Long clienteId, Long compraId) throws BusinessLogicException {
        List<CompraEntity> list = getCliente(clienteId).getCompra();
        CompraEntity compraEntity = compraLogic.getCompra(compraId);
        int index = list.indexOf(compraEntity);
        if (index >= 0) {
            return list.get(index);
        }
        throw new BusinessLogicException("La compra no está asociado al cliente");
    }

    /**
     * @return CompraLogic
     */
    public CompraLogic getCompraLogic() {
        return compraLogic;
    }

    /**
     * @param compraLogic
     */
    public void setCompraLogic(CompraLogic compraLogic) {
        this.compraLogic = compraLogic;
    }

    /**
     * @param clienteId
     * @param list
     * @return ComentarioEntity
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
     * @param clienteId
     * @param entidad
     * @return ComentarioEntity
     * @throws BusinessLogicException
     */
    public ComentarioEntity replaceComentario(Long clienteId, ComentarioEntity entidad) throws BusinessLogicException {
        ComentarioEntity actual = null;
        if (entidad != null && getComentario(clienteId, entidad.getId()) != null) {
            List<ComentarioEntity> comentarioList = comentarioLogic.getComentarios();

            Iterator<ComentarioEntity> it = comentarioList.iterator();
            while (it.hasNext()) {
                actual = it.next();
                if (actual.getId().equals(entidad.getId())) {
                    actual.setName(entidad.getName());
                    break;
                }
            }
        }
        return actual;
    }

    /**
     * @param clienteId
     * @param list
     * @return ObraEntity
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
     * @param clienteId
     * @param comentarioId
     */
    public void removeComentario(Long clienteId, Long comentarioId) {
        ClienteEntity clienteEntity = getCliente(clienteId);
        ComentarioEntity comentario = comentarioLogic.getComentario(comentarioId);
        clienteEntity.getComentarios().remove(comentario);
        comentario.setClienteComentario(null);
        comentarioLogic.deleteComentario(comentarioId);
    }

    /**
     * @param clienteId
     * @param obraId
     */
    public void removeObra(Long clienteId, Long obraId) {
        ClienteEntity clienteEntity = getCliente(clienteId);
        ObraEntity obra = obraLogic.findObra(obraId);
        clienteEntity.getObra().remove(obra);
    }
}
