/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.ClientePersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.ComentarioPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.CompraPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.MarcoPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.ObraPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.carrillor
 */
@Stateless
public class ObraLogic {

    @Inject
    private ObraPersistence persistence;
    @Inject
    private CompraPersistence compraPersistence;

    @Inject
    private ArtistaPersistence artistaPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    @Inject
    private ComentarioPersistence comentarioPersistence;

    @Inject
    private MarcoPersistence marcoPersistence;

    
    public ObraEntity getObra(Long id) {
        ObraEntity obra = persistence.find(id);
        if (obra == null) {
          
        }
          return obra;
    }
    
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ObraEntity createObra(ObraEntity entity) throws BusinessLogicException {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre

        // Invoca la persistencia para crear la Estudiante
        persistence.create(entity);
        return entity;
    }

    /**
     *
     * Obtener todas las Estudiantees existentes en la base de datos.
     *
     * @return una lista de Estudiantees.
     */
    public List<ObraEntity> getObras() {

        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        return persistence.findAll();
    }

    public ObraEntity findObra(Long id) {
        List<ObraEntity> obras = persistence.findAll();
        for (int i = 0; i < obras.size(); i++) {
            ObraEntity actual = obras.get(i);
            if (actual.getId().equals(id)) {
                return actual;
            }
        }
        return null;
    }

    public void delete(Long id) {
        persistence.delete(id);
    }

    public ObraEntity update(ObraEntity entity) {

        return persistence.update(entity);

    }

    public ArtistaEntity getAuthor(Long bookId) {

        return findObra(bookId).getArtista();

    }

    public ArtistaEntity addArtista(Long obraId, ArtistaEntity artista) throws BusinessLogicException {

        ObraEntity obraEntity = findObra(obraId);
        ArtistaEntity artistaNuevo = createArtista(artista);
        List<ObraEntity> listaObras = artistaNuevo.getObras();
        listaObras.add(obraEntity);
        obraEntity.setArtista(artista);

        return artistaNuevo;
    }

    public ArtistaEntity createArtista(ArtistaEntity entity) throws BusinessLogicException {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre
        if (artistaPersistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un id con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la Estudiante
        artistaPersistence.create(entity);
        return entity;
    }

    public ComentarioEntity addComentario(Long obraId, ComentarioEntity comentario) throws BusinessLogicException {
        ObraEntity obra = findObra(obraId);
        ComentarioEntity comentarioNueva = createComentario(comentario);
        comentarioNueva.setObra(obra);
        List<ComentarioEntity> comentariosObra = obra.getComentarios();
        comentariosObra.add(comentario);
        return comentarioNueva;
    }

    public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException {
        comentarioPersistence.create(entity);
        return entity;
    }

    public ClienteEntity addCliente(Long obraId, ClienteEntity cliente) throws BusinessLogicException {

        ObraEntity obraEntity = findObra(obraId);
        ClienteEntity clienteNuevo = createCliente(cliente);
        List<ObraEntity> listaObras = clienteNuevo.getObra();
        listaObras.add(obraEntity);
        obraEntity.setCliente(cliente);

        return clienteNuevo;
    }

    public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException {
        if (clientePersistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un cliente con el id \"" + entity.getId() + "\"");
        }
        return clientePersistence.create(entity);
    }

    public CompraEntity addCompra(Long obraId, CompraEntity compra) throws BusinessLogicException {
        ObraEntity obraEntity = findObra(obraId);
        CompraEntity compraNuevo = createCompra(compra);
        List<ObraEntity> listaObras = compraNuevo.getObras();
        listaObras.add(obraEntity);
        obraEntity.setCompra(compra);

        return compraNuevo;
    }

    public MarcoEntity addMarco(Long obraId, MarcoEntity gal) throws BusinessLogicException {
        ObraEntity obraEntity = findObra(obraId);
        MarcoEntity galNueva = createMarco(gal);
        galNueva.setObra(obraEntity);
        obraEntity.setMarco(galNueva);

        return galNueva;

    }

    public MarcoEntity createMarco(MarcoEntity entity) throws BusinessLogicException {

        marcoPersistence.create(entity);

        return entity;
    }

    public CompraEntity createCompra(CompraEntity entity) throws BusinessLogicException {

        if (compraPersistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una compra con el id \"" + entity.getId() + "\"");
        }

        return compraPersistence.create(entity);
    }

    public ArtistaEntity getArtista(Long obraId) {

        return findObra(obraId).getArtista();
    }

    public List<ComentarioEntity> listComentarios(Long obraId) {
        return findObra(obraId).getComentarios();
    }

}
