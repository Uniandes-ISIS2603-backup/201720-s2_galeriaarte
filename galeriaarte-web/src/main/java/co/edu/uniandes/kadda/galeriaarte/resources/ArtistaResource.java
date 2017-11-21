/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
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
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author jd.carrillor
 */
@Path("artistas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArtistaResource 
{
    
    @Inject
    ArtistaLogic artistaLogic;
    

    @POST
    public ArtistaDetailDTO createArtista(ArtistaDetailDTO artista) throws BusinessLogicException {        
         return new ArtistaDetailDTO(artistaLogic.createArtista(artista.toEntity()));
    }

   @GET
    public List<ArtistaDetailDTO> getArtistas() throws BusinessLogicException {
        return listArtistaEntity2DetailDTO(artistaLogic.getArtistas());
    }

    @GET
    @Path("{id: \\d+}")
    public ArtistaDetailDTO getArtista(@PathParam("id") Long id) throws BusinessLogicException {
        ArtistaEntity entity = artistaLogic.getArtista(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + id + " no existe.", 404);
        }
        return new ArtistaDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ArtistaDetailDTO updateArtista(@PathParam("id") Long id, ArtistaDetailDTO artista) throws BusinessLogicException {
        ArtistaEntity entity = artista.toEntity();
        entity.setId(id);
        ArtistaEntity oldEntity = artistaLogic.getArtista(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El recurso /artistas/" + id + " no existe.", 404);
        }
        entity.setBlogs(oldEntity.getBlogs());
        return new ArtistaDetailDTO(artistaLogic.updateArtista(id, entity));
    }
    
    @DELETE
    @Path("{artistassId: \\d+}")
    public void deleteArtista(@PathParam("artistassId") Long id) throws BusinessLogicException {
        ArtistaEntity entity = artistaLogic.getArtista(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + id + " no existe.", 404);
        }
        artistaLogic.deleteArtista(id);
    }
    
    @Path("{idArtista: \\d+}/blogs")
    public Class<BlogResource> getBlogResource(@PathParam("idArtista") Long artistasId) {
        ArtistaEntity entity = artistaLogic.getArtista(artistasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artistas/" + artistasId + "/blogs no existe.", 404);
        }
        return BlogResource.class;
    }

    private List<ArtistaDetailDTO> listArtistaEntity2DetailDTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
    
    @Path("{id: \\d+}/hojaVida")
    public Class<ArtistaHojaVidaResource> getArtistaHojaVidaResource(@PathParam("id") Long artistaId) {
        ArtistaEntity entity = artistaLogic.getArtista(artistaId);
        if (entity == null) {
            throw new WebApplicationException("El artista no existe", 404);
        }
        return ArtistaHojaVidaResource.class;
    }
    /*/
    @Path("{id: \\d+}/obras")
    public Class<ArtistaObraResource> getArtistaObraResource(@PathParam("id") Long artistaId) {
        ArtistaEntity entity = artistaLogic.findArtista(artistaId);
        if (entity == null) {
            throw new WebApplicationException("El artista no existe", 404);
        }
        return ArtistaObraResource.class;
    }
    /*/
  
    public ArrayList<ArtistaEntity> listDTO2Entity(List<ArtistaDTO> dtoList)
    {
        ArrayList<ArtistaEntity> lista = new ArrayList<ArtistaEntity>();
        for(ArtistaDTO dto : dtoList)
        {
            ArtistaEntity e = new ArtistaEntity();
            e.setId(dto.getId());
            e.setName(dto.getName());
            lista.add(e);
        }
        return lista;
    }
    
}
    