    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.HojaVidaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.HojaVidaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artistas/{id: \\d+}/hojaVida")
@RequestScoped
public class ArtistaHojaVidaResource 
{
   
    
   @Inject
  private  ArtistaLogic artistaLogic;
  
   @Inject
  private  HojaVidaLogic hojaVidaLogic;
  
    @GET 
    public  HojaVidaDetailDTO getHoja (@PathParam("id") Long artista) throws BusinessLogicException
    {
          if (artistaLogic.findArtista(artista)!=null) 
        {
            ArtistaEntity ent = artistaLogic.findArtista(artista);
            HojaVidaEntity hoja = ent.getHojaVida();
          return new HojaVidaDetailDTO(hoja);
         
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
        
    }
    
     @POST   
    public HojaVidaDetailDTO addHoja(@PathParam("id") Long artistaId, HojaVidaDetailDTO dto) throws BusinessLogicException 
    {
        HojaVidaEntity hoja = dto.toEntity();
         return new HojaVidaDetailDTO(artistaLogic.addHoja(artistaId, hoja));
        
    }
    
    @PUT
    public HojaVidaDetailDTO replaceHoja(@PathParam("id") Long artistaId, HojaVidaDetailDTO dto) throws BusinessLogicException
    {
        
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        HojaVidaEntity oldHoja = artista.getHojaVida();
        HojaVidaEntity hojaNueva = dto.toEntity();
          hojaNueva.setId(oldHoja.getId());
          hojaNueva.setArtista(artista);
             
           return new HojaVidaDetailDTO(hojaVidaLogic.update(hojaNueva));
            
      
    
    }
    
     @DELETE
    public void removeHoja(@PathParam("id") Long artistaId) throws BusinessLogicException
    {
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        HojaVidaEntity hojaEliminar = artista.getHojaVida();
        
        hojaVidaLogic.delete(hojaEliminar.getId());
    }
    
    
    
    

  
    
     
    
    
}
