/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.ObraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Perilla
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CatalogoObraResource 
{
     @Inject
    private CatalogoLogic catalogoLogic;
    
    @Inject
    private ObraLogic obraLogic;
    
    /**
     * Convierte una lista de ObraEntity a una lista de ObraDetailDTO.
     *
     * @param entityList Lista de ObraEntity a convertir.
     * @return Lista de ObraDetailDTO convertida.
     * 
     */
    private List<ObraDTO> obrasListEntity2DTO(List<ObraEntity> entityList) {
        List<ObraDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) {
            list.add(new ObraDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de ObraDetailDTO a una lista de ObraEntity.
     *
     * @param dtos Lista de ObraDetailDTO a convertir.
     * @return Lista de ObraEntity convertida.
     * 
     */
    private List<ObraEntity> obrasListDTO2Entity(List<ObraDTO> dtos) {
        List<ObraEntity> list = new ArrayList<>();
        for (ObraDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Obtiene una colección de instancias de ObraDetailDTO asociadas a una
     * instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @return Colección de instancias de ObraDetailDTO asociadas a la
     * instancia de Catalogo
     * 
     */
    @GET
   public List<ObraDTO> listObras(@PathParam("catalogoId") Long catalogoId) {
        return obrasListEntity2DTO(catalogoLogic.listObras(catalogoId));
   }
   

    /**
     * Remplaza las instancias de Obra asociadas a una instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @param obras
     * @param List<ObraDTO> Colección de instancias de ObraDTO a asociar a instancia
     * de Catalogo
     * @return Nueva colección de ObraDTO asociada a la instancia de Catalogo
     * 
     */
   @PUT
   public List<ObraDTO> replaceObras(@PathParam("catalogoId") Long catalogoId, List<ObraDTO> obras)
   {
       return obrasListEntity2DTO(catalogoLogic.replaceObras(catalogoId, obrasListDTO2Entity(obras)));
    }

    /**
     * Desasocia una Obra existente de un Catalogo existente
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @param obraId Identificador de la instancia de Obra
     * 
     */
    @DELETE
    @Path("{obraId: \\d+}")
    public void removeObra(@PathParam("catalogoId") Long catalogoId, @PathParam("obraId") Long obraId) 
    {
       catalogoLogic.removeObra(catalogoId, obraId);
    }
   
}
