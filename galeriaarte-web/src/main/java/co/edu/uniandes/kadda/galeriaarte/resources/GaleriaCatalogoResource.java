/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.CatalogoDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Perilla
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GaleriaCatalogoResource {

    @Inject
    private CatalogoLogic catalogoLogic;

    @Inject
    private GaleriaLogic galeriaLogic;

    /**
     * Obtiene una colección de instancias de CatalogoDetailDTO asociadas a una
     * instancia de Catalogo
     *
     * @return Colección de instancias de CatalogoDetailDTO asociadas a la
     * instancia de Galeria
     *
     */
    @GET
    public List<CatalogoDetailDTO> listCatalogo() {
        return new ArrayList<CatalogoDetailDTO>();
    }

    /**
     * Remplaza las instancias de Catalogos asociadas a una instancia de Galeria
     *
     * @return Nueva colección de CatalogoDTO asociada a la instancia de Galeria
     *
     */
    // Además tenicamente no lo puedo hacer porque no puedo cambiar las obras que estan dentro de catálogo.
    @PUT
    public List<CatalogoDTO> replaceCatalogos() {
        return null;
    }
}
