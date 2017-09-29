/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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

@Path("obras/{id: \\d+}/artista")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class ObraArtistaResource 
{
    /*/
@Inject
ArtistaLogic artistaLogic;
@Inject
ObraLogic obraLogic;  


    @GET 
    public  ArtistaDetailDTO getArtista (@PathParam("id") Long obra) throws BusinessLogicException
    {
          if (obraLogic.findObra(obra)!=null) 
        {
        ObraEntity ent = obraLogic.findObra(obra);
        
        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + obra);
        }
            ArtistaEntity artista =ent.getArtista();
            return new ArtistaDetailDTO(artista);
    
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
    }
    
     @POST   
    public ArtistaDetailDTO addArtista(@PathParam("id") Long obraId, ArtistaDetailDTO dto) throws BusinessLogicException 
    {
        ArtistaEntity artista = dto.toEntity();
         return new ArtistaDetailDTO(obraLogic.addArtista(obraId, artista));
        
    }
    /*/
    
}
