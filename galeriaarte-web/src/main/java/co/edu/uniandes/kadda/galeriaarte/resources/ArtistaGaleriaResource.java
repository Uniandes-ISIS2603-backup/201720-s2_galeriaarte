    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
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

@Path("artistas/{id: \\d+}/galeriaArte")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistaGaleriaResource
{
@Inject
ArtistaLogic artistaLogic;
@Inject
GaleriaLogic galeriaLogic;

@GET 
    public  GaleriaDetailDTO getGaleria (@PathParam("id") Long artistaId) throws BusinessLogicException
    {
        if (artistaLogic.findArtista(artistaId)!=null) 
        {
        ArtistaEntity ent = artistaLogic.findArtista(artistaId);
        
        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + artistaId);
        }
            GaleriaEntity gal =ent.getGaleria();
            return new GaleriaDetailDTO(gal);
    
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
    }
    
    
    @POST   
    public GaleriaDetailDTO addGaleria(@PathParam("id") Long artistaId, GaleriaDetailDTO dto) throws BusinessLogicException 
    {
        GaleriaEntity gal = dto.toEntity();
         return new GaleriaDetailDTO(artistaLogic.addGaleria(artistaId, gal));
        
    }
    
    
    @PUT
    
    public GaleriaDetailDTO replaceGaleria(@PathParam("id") Long artistaId, GaleriaDetailDTO dto) throws BusinessLogicException
    {
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        GaleriaEntity viejo = artista.getGaleria();
        GaleriaEntity nuevo = dto.toEntity();
        nuevo.setId(viejo.getId());
        nuevo.setArtistas(viejo.getArtistas());
        return new GaleriaDetailDTO(galeriaLogic.update(nuevo));
        
    
    }    
    
    @DELETE
    
    public void removeGaleria(@PathParam("id") Long artistaId) throws BusinessLogicException
    {
        
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        GaleriaEntity galeria = artista.getGaleria();
        artistaLogic.delete(galeria.getId());
            
        
    }
 
    
    
}
