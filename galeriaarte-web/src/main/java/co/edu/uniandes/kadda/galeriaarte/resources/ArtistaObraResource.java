/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
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

import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.carrillor
 */
@Path("artistas/{id: \\d+}/obras")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistaObraResource {

    @Inject
    ArtistaLogic artistaLogic;
    @Inject
    ObraLogic obraLogic;

    @GET
    public List<ObraDetailDTO> getObras(@PathParam("id") Long artista) throws BusinessLogicException {
        if (artistaLogic.getArtista(artista) != null) {
            ArtistaEntity ent = artistaLogic.getArtista(artista);
            if (ent == null) {
                throw new BusinessLogicException("No existe el proveedor con id " + artista);
            }
            List<ObraDetailDTO> list = new ArrayList();
            for (ObraEntity fbEntity : artistaLogic.listObras(artista)) {
                list.add(new ObraDetailDTO(fbEntity));
            }
            return list;
        } else {
            throw new BusinessLogicException("error");
        }

    }

    @GET
    @Path("/{idObra:\\d+}")
    public ObraDetailDTO getObra(@PathParam("id") Long idProveedor, @PathParam("idObra") Long idFeedBack) throws BusinessLogicException {
        ArtistaEntity ent = artistaLogic.getArtista(idProveedor);
        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        }
        ObraDetailDTO fbDetail = null;
        for (ObraEntity fb : ent.getObras()) {
            if (fb.getId().equals(idFeedBack)) {
                fbDetail = new ObraDetailDTO(fb);
            }
        }
        if (fbDetail == null) {
            throw new BusinessLogicException("No existe la obra con id " + idFeedBack + " del proveedor con id " + idProveedor);
        }
        return fbDetail;
    }

    @POST
    public ObraDetailDTO addObra(@PathParam("id") Long artistaId, ObraDetailDTO dto) throws BusinessLogicException {
        ObraEntity obra = dto.toEntity();
        return new ObraDetailDTO(artistaLogic.addObra(artistaId, obra));

    }

    @PUT
    @Path("/{idObra:\\d+}")
    public ObraDetailDTO replaceBooks(@PathParam("id") Long artistaId, ObraDetailDTO dto, @PathParam("idObra") Long idObra) throws BusinessLogicException {

        ArtistaEntity artista = artistaLogic.getArtista(artistaId);
        List<ObraEntity> obras = artista.getObras();
        for (int i = 0; i < obras.size(); i++) {
            ObraEntity actual = obras.get(i);
            if (actual.getId().equals(idObra)) {
                ObraEntity mod = dto.toEntity();
                mod.setId(idObra);
                mod.setArtista(artista);
                return new ObraDetailDTO(obraLogic.update(mod));
            }
        }

        return null;

    }

    @DELETE
    @Path("/{idObra:\\d+}")
    public void removeObra(@PathParam("id") Long artistaId, @PathParam("idObra") Long idObra) throws BusinessLogicException {
        ArtistaEntity artista = artistaLogic.getArtista(artistaId);
        List<ObraEntity> obrasArtista = artista.getObras();
        for (int i = 0; i < obrasArtista.size(); i++) {
            ObraEntity obraActual = obrasArtista.get(i);
            if (obraActual.getId().equals(idObra)) {
                obraLogic.delete(idObra);
            }
        }
    }

}
