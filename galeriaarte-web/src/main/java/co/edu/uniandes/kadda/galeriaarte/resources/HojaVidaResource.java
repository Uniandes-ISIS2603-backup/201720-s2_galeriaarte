/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;
import co.edu.uniandes.kadda.galeriaarte.dtos.HojaVidaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.HojaVidaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
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



/**
 *
 * @author jd.carrillor
 */
@Path("hojasVida")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HojaVidaResource 
{
 @Inject
    HojaVidaLogic hojaVidaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

  
    @POST
    public HojaVidaDetailDTO createHojaVida(HojaVidaDetailDTO hojaVida) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        HojaVidaEntity hojaEntity = hojaVida.toEntity();
        // Invoca la lógica para crear la Estudiante nueva
        HojaVidaEntity nuevaObra = hojaVidaLogic.createHojaVida(hojaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new HojaVidaDetailDTO(nuevaObra);
    }

    
   @GET
    public List<HojaVidaDetailDTO> getHojasVida() throws BusinessLogicException {
        return listEntity2DetailDTO(hojaVidaLogic.getHojasVida());
    }

    
    @GET
    @Path("{id: \\d+}")
    public HojaVidaDetailDTO getHojaVida(@PathParam("id") Long id) throws WebApplicationException
    {
        if (hojaVidaLogic.findHoja(id)!=null) 
        {
            HojaVidaDetailDTO c = new HojaVidaDetailDTO(hojaVidaLogic.findHoja(id));
            return c;
        }
        else {
            throw new WebApplicationException("Error3");
        }
        
    }
    
   
    @PUT
    @Path("{id: \\d+}")
    public HojaVidaDetailDTO updateHojaVida(@PathParam("id") Long id, HojaVidaDetailDTO hoja) throws BusinessLogicException, UnsupportedOperationException {
         
        
       HojaVidaEntity entity = hoja.toEntity();
        entity.setId(id);
        
        HojaVidaEntity oldEntity = hojaVidaLogic.findHoja(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
       
        return new HojaVidaDetailDTO(hojaVidaLogic.update(entity));
      
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
    public void deleteHojaVida(@PathParam("id") Long id) throws BusinessLogicException 
    {
        if (hojaVidaLogic.findHoja(id)!=null)
        {
          hojaVidaLogic.delete(id);
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
    private List<HojaVidaDetailDTO> listEntity2DetailDTO(List<HojaVidaEntity> entityList) {
        List<HojaVidaDetailDTO> list = new ArrayList<>();
        for (HojaVidaEntity entity : entityList) {
            list.add(new HojaVidaDetailDTO(entity));
        }
        return list;
    }
    
    
    
}
