/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Perilla
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GaleriaArtistaResource 
{
    @Inject
    private ArtistaLogic artistaLogic;
    
    @Inject 
    private GaleriaLogic galeriaLogic;
    
    
    
    /**
     * Convierte una lista de ArtistaEntity a una lista de ArtistaDetailDTO.
     *
     * @param entityList Lista de ArtistaEntity a convertir.
     * @return Lista de ArtistaDetailDTO convertida.
     * 
     */
    private List<ArtistaDetailDTO> artistaListEntity2DTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
    
    /**dx
     * Convierte una lista de ArtistaDTO a una lista de ArtistaEntity.
     *
     * @param dtos Lista de ArtistaDetailDTO a convertir.
     * @return Lista de ArtistaEntity convertida.
     * 
     */
    private List<ArtistaEntity> artistaListDTO2Entity(List<ArtistaDTO> dtos) {
        List<ArtistaEntity> list = new ArrayList<>();
        for (ArtistaDTO dto : dtos) 
        {
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
    //@GET
    //public List<CatalogoDetailDTO> listCatalogo(@PathParam("galeriaId") Long galeriaId) {
    //    return catalogoListEntity2DTO(galeriaLogic.listCatalogo(galeriaId));
    //List<CatalogoDetailDTO> resp = new ArrayList<CatalogoDetailDTO>();
    //return resp;
    //}
    
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
    //GaleriaDetailDTO resp = new GaleriaDetailDTO(); 
    //return resp;
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
   //@PUT
   //public List<CatalogoDTO> replaceCatalogos(@PathParam("galeriaId") Long galeriaId, List<CatalogoDTO> catalogos) 
   //{
    //   return catalogoListEntity2DTO(galeriaLogic.replaceCatalogos(galeriaId, catalogoListDTO2Entity(catalogos)));
     //  return null;
  // }   
}
