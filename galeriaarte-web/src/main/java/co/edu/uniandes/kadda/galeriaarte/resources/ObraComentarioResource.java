/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ComentarioDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ComentarioLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.carrillor
 */
@Path("obras/{id: \\d+}/comentarios")
 @RequestScoped
 @Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObraComentarioResource 
{
 @Inject
ObraLogic obraLogic;
 
 @Inject
ComentarioLogic cometarioLogic;
 
 @GET 
    public  List<ComentarioDetailDTO> getObras (@PathParam("id") Long obraId) throws BusinessLogicException
    {
          if (obraLogic.findObra(obraId)!=null) 
        {
            ObraEntity ent = obraLogic.findObra(obraId);
            if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + obraId);
        List<ComentarioDetailDTO> list = new ArrayList();
        for (ComentarioEntity coment : obraLogic.listComentarios(obraId))
            list.add(new ComentarioDetailDTO(coment));
        return list;
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
        
    }
    
    @GET
    @Path(("/{idObra:\\d+}"))
    public ComentarioDetailDTO getComentario( @PathParam("id") Long artistaId, @PathParam("idComentario") Long idComentario) throws BusinessLogicException
    {
        ObraEntity ent = obraLogic.findObra(artistaId);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + artistaId);
        ComentarioDetailDTO comentarioDetail = null;
        for (ComentarioEntity comentario : ent.getComentarios())
            if (comentario.getId().equals(idComentario)) comentarioDetail = (new ComentarioDetailDTO(comentario));
        if (comentarioDetail == null) throw new BusinessLogicException("No existe la obra con id " + idComentario + " del proveedor con id " + artistaId);
        return comentarioDetail;
    }
    
    
     @POST   
    public ComentarioDetailDTO addComentario(@PathParam("id") Long obraId, ComentarioDetailDTO dto) throws BusinessLogicException 
    {
        ComentarioEntity comentario = dto.toEntity();
         return new ComentarioDetailDTO(obraLogic.addComentario(obraId, comentario));
        
    }
 
 
}
