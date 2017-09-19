/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.carrillor
 */
@Path("artistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class ArtistaResource 
{
    
    @Inject
    ArtistaLogic artistaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

  
    @POST
    public ArtistaDetailDTO createArtista(ArtistaDetailDTO artista) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ArtistaEntity artistaEntity = artista.toEntity();
        // Invoca la lógica para crear la Estudiante nueva
        ArtistaEntity nuevoArtista = artistaLogic.createArtista(artistaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ArtistaDetailDTO(nuevoArtista);
    }

    
   @GET
    public List<ArtistaDetailDTO> getArtistas() throws BusinessLogicException {
        return listEntity2DetailDTO(artistaLogic.getArtistas());
    }

    
    @GET
    @Path("{id: \\d+}")
    public ArtistaDetailDTO getArtista(@PathParam("id") Long id) throws WebApplicationException
    {
        if (artistaLogic.findArtista(id)!=null) 
        {
            ArtistaDetailDTO c = new ArtistaDetailDTO(artistaLogic.findArtista(id));
            return c;
        }
        else {
            throw new WebApplicationException("Error3");
        }
        
    }
    
   
    @PUT
    @Path("{id: \\d+}")
    public ArtistaDetailDTO updateArtista(@PathParam("id") Long id, ArtistaDetailDTO artista) throws BusinessLogicException, UnsupportedOperationException {
         
        
        if (artistaLogic.findArtista(id)!=null)
        {
            ArtistaEntity artistaEntity = artista.toEntity();
            
            artistaLogic.update(artistaEntity);
        }
        
        else{
            
        
        throw new WebApplicationException("Error");
        }
        
        if (!artista.getId().equals(id))
        {
            throw new BusinessLogicException("Error2");
        }
        
        return artista;
      
    }

    /**
     * DELETE http://localhost:8080/estudiante-web/api/estudiantes/1
     *
     * @param id corresponde a la Estudiante a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Estudiante a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEstudiante(@PathParam("id") Long id) throws BusinessLogicException 
    {
        if (artistaLogic.findArtista(id)!=null)
        {
          artistaLogic.delete(id);
        }
        
        else{
            throw new WebApplicationException("Este servicio no está implementado");
        }
         
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EstudianteEntity a una lista de
     * objetos EstudianteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Estudiantees de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Estudiantees en forma DTO (json)
     */
    private List<ArtistaDetailDTO> listEntity2DetailDTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
    
}
