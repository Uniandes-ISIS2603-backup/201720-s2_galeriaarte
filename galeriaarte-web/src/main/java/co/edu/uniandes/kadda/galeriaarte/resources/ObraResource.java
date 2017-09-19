/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
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
@Path("obras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class ObraResource
{
    @Inject
    ObraLogic obraLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

  
    @POST
    public ObraDetailDTO createObra(ObraDetailDTO obra) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ObraEntity obraEntity = obra.toEntity();
        // Invoca la lógica para crear la Estudiante nueva
        ObraEntity nuevaObra = obraLogic.createObra(obraEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ObraDetailDTO(nuevaObra);
    }

    
   @GET
    public List<ObraDetailDTO> getObras() throws BusinessLogicException {
        return listEntity2DetailDTO(obraLogic.getObras());
    }

    
    @GET
    @Path("{id: \\d+}")
    public ObraDetailDTO getObra(@PathParam("id") Long id) throws WebApplicationException
    {
        if (obraLogic.findObra(id)!=null) 
        {
            ObraDetailDTO c = new ObraDetailDTO(obraLogic.findObra(id));
            return c;
        }
        else {
            throw new WebApplicationException("Error3");
        }
        
    }
    
   
    @PUT
    @Path("{id: \\d+}")
    public ObraDetailDTO updateObra(@PathParam("id") Long id, ObraDetailDTO obra) throws BusinessLogicException, UnsupportedOperationException {
         
        
        if (obraLogic.findObra(id)!=null)
        {
            ObraEntity obraEntity = obra.toEntity();
            
            obraLogic.update(obraEntity);
        }
        
        else{
            
        
        throw new WebApplicationException("Error");
        }
        
        if (!obra.getId().equals(id))
        {
            throw new BusinessLogicException("Error2");
        }
        
        return obra;
      
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
    public void deleteObra(@PathParam("id") Long id) throws BusinessLogicException 
    {
        if (obraLogic.findObra(id)!=null)
        {
          obraLogic.delete(id);
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
    private List<ObraDetailDTO> listEntity2DetailDTO(List<ObraEntity> entityList) {
        List<ObraDetailDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) {
            list.add(new ObraDetailDTO(entity));
        }
        return list;
    }
    
}
