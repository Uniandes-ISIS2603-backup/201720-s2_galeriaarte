/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;



import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 * Clase que implementa el recurso REST correspondiente a "Galerias".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Galerias". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/Galerias"
 *
 * @author ISIS2603
 *
 */
@Path("galerias")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class GaleriaResource {

    @Inject
    GaleriaLogic galeriaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    CatalogoLogic catalogoLogic;
    
    @Inject
    ArtistaLogic artistaLogic;
    
    @Inject
    ClienteLogic clienteLogic;
    
    private GaleriaDTO galeriaDTO;
    private GaleriaDetailDTO galeriaDetailDTO;
    
    
    
    
 

    private static final Logger LOGGER = Logger.getLogger(GaleriaResource.class.getName());

    /**
     * POST http://localhost:8080/galeriadearte-web/api/galerias
     *
     * @param galeria
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "GaleriaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public GaleriaDetailDTO createGaleria(GaleriaDetailDTO galeria) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        GaleriaEntity galeriaEntity = galeria.toEntity();
        // Invoca la lógica para crear la Galeria nueva
        GaleriaEntity nuevoGaleria = galeriaLogic.createGaleria(galeriaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new GaleriaDetailDTO(nuevoGaleria);
    }

    /**
     * GET para todas las Galerias.
     * http://localhost:8080/galeriadearte-web/api/Galerias
     *
     * @return la lista de todas las Galerias en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<GaleriaDetailDTO> getGalerias() throws BusinessLogicException {
        return listEntity2DetailDTO(galeriaLogic.getGalerias());
    }

    /**
     * PUT http://localhost:8080/galeriadearte-web/api/galerias/1 Ejemplo json {
     * "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Galeria a actualizar.
     * @param galeriadearte corresponde al objeto con los cambios que se van a
     * realizar.
     * @return La Galeria actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Galeria a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public GaleriaDetailDTO updateGaleria(@PathParam("id") Long id, GaleriaDetailDTO galeriadearte) throws BusinessLogicException, UnsupportedOperationException, WebApplicationException {

        GaleriaEntity entity = galeriaDTO.toEntity();
        entity.setId(id);
        GaleriaEntity vieja = galeriaLogic.getGaleria();
        if(vieja == null)
        {
            throw new WebApplicationException("La galería no existe", 404);
        }
        else
        {
            entity.setArtistas(vieja.getArtistas());
            entity.setCatalogos(vieja.getCatalogos());
            entity.setClientes(vieja.getClientes());
        }
        
        return new GaleriaDetailDTO(galeriaLogic.updateGaleria(entity));
    }


    /**
     * DELETE http://localhost:8080/galeriadearte-web/api/galerias/{id}
     *
     * @param id corresponde a la Galeria a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Galeria a actualizar se retorna un 404
     * con el mensaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteGaleria(@PathParam("id") Long id) throws BusinessLogicException
    {
       GaleriaEntity entity = galeriaLogic.getGaleria();
       if(entity == null)
       {
           throw new WebApplicationException("La galería no existe", 404);
       }
       galeriaLogic.deleteGaleria(id);
    }
    

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos GaleriaEntity a una lista de
     * objetos GaleriaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Galerias de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Galerias en forma DTO (json)
     */
    private List<GaleriaDetailDTO> listEntity2DetailDTO(List<GaleriaEntity> entityList) {
        List<GaleriaDetailDTO> list = new ArrayList<>();
        for (GaleriaEntity entity : entityList) {
            list.add(new GaleriaDetailDTO(entity));
        }
        return list;
    }

   
    

    

}
