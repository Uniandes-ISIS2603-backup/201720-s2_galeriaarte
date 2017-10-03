/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class GaleriaCatalogoResource 
{
 @Inject
    private CatalogoLogic catalogoLogic;
    
    @Inject 
    private GaleriaLogic galeriaLogic;
    
    
    
    /**
     * Convierte una lista de CatalogoEntity a una lista de CatalogoDetailDTO.
     *
     * @param entityList Lista de CatalogoEntity a convertir.
     * @return Lista de CatalogoDetailDTO convertida.
     * 
     */
    private List<CatalogoDetailDTO> catalogoListEntity2DTO(List<CatalogoEntity> entityList) {
        List<CatalogoDetailDTO> list = new ArrayList<>();
        for (CatalogoEntity entity : entityList) {
            list.add(new CatalogoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de CatalogoDTO a una lista de CatalogoEntity.
     *
     * @param dtos Lista de CatalogoDetailDTO a convertir.
     * @return Lista de CatalogoEntity convertida.
     * 
     */
    private List<CatalogoEntity> catalogoListDTO2Entity(List<CatalogoDTO> dtos) {
        List<CatalogoEntity> list = new ArrayList<>();
        for (CatalogoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Obtiene una colección de instancias de CatalogoDetailDTO asociadas a una
     * instancia de Catalogo
     *
     * @param catalogoId Identificador de la instancia de Catalogo
     * @return Colección de instancias de CatalogoDetailDTO asociadas a la
     * instancia de Galeria
     * 
     */
    @GET
    public List<CatalogoDetailDTO> listCatalogo(@PathParam("galeriaId") Long galeriaId) {
    //    return catalogoListEntity2DTO(galeriaLogic.listCatalogo(galeriaId));
    List<CatalogoDetailDTO> resp = new ArrayList<CatalogoDetailDTO>();
        return resp;
    }
    
    /**
     * Asocia una Galeria existente a una Catalogo
     *
     * @param catalogoId Identificador de la instancia de catálogo
     * @param galeriaId Identificador de la instancia de galería
     * @return Instancia de galeriaDetailDTO que fue asociada a catálogo
     * 
     */
    //@POST
    //@Path("{catalogoId: \\d+}")
    //public GaleriaDetailDTO addGaleria(@PathParam("catalogoId") Long catalogoId, @PathParam("galeriaId") Long galeriaId) {
    //    return new GaleriaDetailDTO(catalogoLogic.addGaleria(catalogoId, galeriaId));
    //}

    /**
     * Remplaza las instancias de Catalogos asociadas a una instancia de Galeria
     *
     * @param galeriaId Identificador de la instancia de Galeria
     * @param List<CatalogoDTO> Colección de instancias de CatalogoDTO a asociar a instancia
     * de Galeria
     * @return Nueva colección de CatalogoDTO asociada a la instancia de Galeria
     * 
     */
    
    // NO ENTIENDO POR QUE ME SALE ERROR EN ESTE MÉTODO SI EL MÉTODO CATALOGOLISTDTO2ENTITY YA ESTA HECHO CON LAS ESPECIFÍCACIONES NECESARIAS.
    // Además tenicamente no lo puedo hacer porque no puedo cambiar las obras que estan dentro de catálogo.
   @PUT
   public List<CatalogoDTO> replaceCatalogos(@PathParam("galeriaId") Long galeriaId, List<CatalogoDTO> catalogos) 
   {
    //   return catalogoListEntity2DTO(galeriaLogic.replaceCatalogos(galeriaId, catalogoListDTO2Entity(catalogos)));
       return null;
   }   
}
