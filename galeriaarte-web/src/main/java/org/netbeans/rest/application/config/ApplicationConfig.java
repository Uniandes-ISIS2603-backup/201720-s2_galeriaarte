/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jd.carrillor
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.kadda.galeriaarte.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.mappers.UnsupportedOperationExceptionMapper.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ArtistaBlogResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ArtistaGaleriaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ArtistaHojaVidaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ArtistaObraResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ArtistaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.BlogResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.CatalogoResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ClienteComentarioResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ClienteResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ComentarioResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.CompraObrasResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.CompraResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.GaleriaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.HojaVidaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.MarcoResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ObraArtistaResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ObraClienteResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ObraComentarioResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ObraCompraResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.ObraResource.class);
        resources.add(co.edu.uniandes.kadda.galeriaarte.resources.PagoResource.class);
    }
    
}
