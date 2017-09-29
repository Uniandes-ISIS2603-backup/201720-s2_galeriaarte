/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.BlogLogic;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author jd.carrillor
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artistas/{id: \\d+}/blogs")
@RequestScoped
public class ArtistaBlogResource 
{
    /*/
  @Inject
  ArtistaLogic artistaLogic;
  
  @Inject
  BlogLogic blogLogic;
  /*/
 
    
    
    
    
    
    
    
}
