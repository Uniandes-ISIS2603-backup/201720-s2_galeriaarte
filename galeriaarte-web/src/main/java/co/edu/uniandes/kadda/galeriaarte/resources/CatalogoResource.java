/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

/**
 *Clase que implementa el recurso REST correspondiente a "catalogo"
 * 
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" 
 * y este recurso tiene la ruta "catalogo". Al ejecutar la aplicación, el recurso
 * será accesible a través de la ruta "/api/books"
 * @author Daniel Perilla
 */
@Path("catalogos")
@Produces("application/json")
@Consumes("application/json")
public class CatalogoResource {
    
    @Inject
    CatalogoLogic catalogoLogic;
    
    String error = "El catalogo no existe";
    
    @GET
    public List<CatalogoDetailDTO> getCatalogos() throws BusinessLogicException
    {
        return listCatalogoEntity2DetailDTO(catalogoLogic.getCatalogos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public CatalogoDetailDTO getCatalogo(@PathParam("id") Long id) throws BusinessLogicException
    {
        CatalogoEntity entity = catalogoLogic.getCatalogo(id);
        if(entity == null)
        {
            throw new WebApplicationException(error, 404);
        }
        return new CatalogoDetailDTO(entity);
    }
    
    @POST
    public CatalogoDetailDTO crearCatalogo(CatalogoDetailDTO catalogo) throws BusinessLogicException
    {
        return new CatalogoDetailDTO(catalogoLogic.crearCatalogo(catalogo.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CatalogoDetailDTO updateCatalogo(@PathParam("id") Long id, CatalogoDetailDTO catalogo) throws BusinessLogicException
    {
        CatalogoEntity entity = catalogoLogic.getCatalogo(id);
        if(entity == null)
        {
            throw new WebApplicationException(error, 404);
        }
        return new CatalogoDetailDTO(catalogoLogic.updateCatalogo(id, catalogo.toEntity()));
    }

    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCatalogo(@PathParam("id") Long id) throws BusinessLogicException
    {
        CatalogoEntity entity = catalogoLogic.getCatalogo(id);
        if(entity == null)
        {
            throw new WebApplicationException(error, 404);
        }
        catalogoLogic.deleteCatalogo(id);
    }
    
    
    private List<CatalogoDetailDTO> listCatalogoEntity2DetailDTO(List<CatalogoEntity> entityList) {
        List<CatalogoDetailDTO> list = new ArrayList<>();
        for (CatalogoEntity entity : entityList) {
            list.add(new CatalogoDetailDTO(entity));
        }
        return list;
    }
    
    public List<CatalogoEntity> listDTO2Entity(List<CatalogoDTO> dtoList)
    {
        ArrayList<CatalogoEntity> lista = new ArrayList<CatalogoEntity>();
        for(CatalogoDTO dto : dtoList)
        {
            CatalogoEntity e = new CatalogoEntity();
            e.setCategoria(dto.getCategoria());
            e.setId(dto.getId());
            lista.add(e);
        }
        return lista;
    }
}
    
    