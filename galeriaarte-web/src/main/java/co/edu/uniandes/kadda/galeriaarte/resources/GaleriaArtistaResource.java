/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.ArtistaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.GaleriaDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
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
    
    /**
     * Convierte una lista de ArtistaDTO a una lista de ArtistaEntity.
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
     * @param galeriaId
     * @return Colección de instancias de CatalogoDetailDTO asociadas a la
     * instancia de Galeria
     * 
     */
    @GET
    public List<ArtistaDetailDTO> listArtistas(@PathParam("galeriaId") Long galeriaId)
    {
        return artistaListEntity2DTO(galeriaLogic.getArtistas());
    }
    
    /**
     * Asocia una Galeria existente a una Catalogo
     *
     * @param artistaId
     * @param galeriaId Identificador de la instancia de galería
     * @return Instancia de galeriaDetailDTO que fue asociada a artista
     * 
     */
    @POST
    @Path("{artistaId: \\d+}")
    public GaleriaDetailDTO addGaleria(@PathParam("artistaId") Long artistaId, @PathParam("galeriaId") Long galeriaId) throws BusinessLogicException
    {
        return new GaleriaDetailDTO(artistaLogic.addGaleria(artistaId, galeriaLogic.getGaleria()));
    }

    /**
     * Remplaza las instancias de Artistas asociadas a una instancia de Galeria
     *
     * @param artistas
     * @return Nueva colección de ArtistaDTO asociada a la instancia de Galeria
     * 
     */
   
   @PUT
   public List<ArtistaDetailDTO> replaceArtistas(List<ArtistaDTO> artistas) 
   {
       return artistaListEntity2DTO(galeriaLogic.replaceArtistas(artistaListDTO2Entity(artistas)));
   }   
}
