/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.HojaVidaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
@Stateless
public class ArtistaHojaVidaResource 
{
   
  private static ArtistaLogic artistaLogic;
  
  
  
    

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     * 
     */
    private HojaVidaDetailDTO hojaVidaEntity2DTO(HojaVidaEntity entity){
    
      return new HojaVidaDetailDTO(entity);
        
      
           
        
        
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     * 
     */
    private HojaVidaEntity  hojaVidaDTO2Entity(HojaVidaDetailDTO  dtos){
       
       
           return (dtos.toEntity());
        
       
    }

    /**
     * Obtiene una colección de instancias de BookDetailDTO asociadas a una
     * instancia de Author
     *
     * 
     * @param artistaId
     * @return Colección de instancias de BookDetailDTO asociadas a la instancia de Author
     * 
     */
    @GET
    public  HojaVidaDetailDTO getHojaVida(@PathParam("id") Long artistaId)
    {
        if(artistaLogic.getHojaVida(artistaId)!=null){
        return new HojaVidaDetailDTO(artistaLogic.getHojaVida(artistaId));
     }
        System.out.println("ssasa");
        HojaVidaDetailDTO x= new HojaVidaDetailDTO();
        x.setId(artistaId);
        return x;
                
    }

    

    @POST
    @Path("{hojVidaId: \\d+}")
    public HojaVidaDetailDTO addHojaVida(@PathParam("artistaId") Long artistaId, @PathParam("hojaVidaId") Long hojaVidaId) {
        return new HojaVidaDetailDTO(artistaLogic.addHojaVida(artistaId, hojaVidaId));
    }

    
    
    @PUT
    public HojaVidaDetailDTO replaceHojaVida (@PathParam("artistaId") Long artistaId, HojaVidaDetailDTO hoja) {
        return hojaVidaEntity2DTO(artistaLogic.replaceHojaVida(artistaId, hojaVidaDTO2Entity(hoja)));
    }

   
    
    @DELETE
    @Path("{booksId: \\d+}")
    public void removeBooks(@PathParam("authorsId") Long artistaId) {
        artistaLogic.removeHojaVida(artistaId);
    } 
    
     
    
    
}
