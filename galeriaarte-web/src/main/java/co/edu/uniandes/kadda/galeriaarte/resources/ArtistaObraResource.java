/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaObraLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
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
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jd.carrillor
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ArtistaObraResource
{
   /*/
    final ArtistaObraLogic artistaObraLogic; 
    
    @Inject
     public ArtistaObraResource(ArtistaObraLogic x)
     {
         artistaObraLogic = x;
     }
     
     public ArtistaObraResource()
     {
         artistaObraLogic = new ArtistaObraLogic();
     }
    
 private List<ObraDetailDTO> booksListEntity2DTO(List<ObraEntity> entityList){
        List<ObraDetailDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) {
            list.add(new ObraDetailDTO(entity));
        }
        return list;
    }
 
 
 private List<ObraEntity> booksListDTO2Entity(List<ObraDetailDTO> dtos){
        List<ObraEntity> list = new ArrayList<>();
        for (ObraDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
 
  @GET
    public List<ObraDetailDTO> listObras(@PathParam("id") Long authorsId) throws Exception
    {
        
        return booksListEntity2DTO(artistaObraLogic.listObras(authorsId));
    }

        
         throw new WebApplicationException("Error");
    }
 

/*/
}
