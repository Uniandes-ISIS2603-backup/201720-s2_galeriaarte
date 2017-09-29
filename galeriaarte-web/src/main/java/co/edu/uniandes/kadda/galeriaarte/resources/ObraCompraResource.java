/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.CompraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.CompraLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
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

/**
 *
 * @author jd.carrillor
 */

@Path("obras/{id: \\d+}/compra")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObraCompraResource
{
    @Inject
ObraLogic obraLogic;  
    
     @Inject
CompraLogic compraLogic;  
     
     
     
    @GET 
    public  CompraDetailDTO getCompra (@PathParam("id") Long obra) throws BusinessLogicException
    {
          if (obraLogic.findObra(obra)!=null) 
        {
        ObraEntity ent = obraLogic.findObra(obra);
        
        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + obra);
        }
            CompraEntity compra =ent.getCompra();
            return new CompraDetailDTO(compra);
    
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
    }
    
    @POST   
    public CompraDetailDTO addCompra(@PathParam("id") Long obraId, CompraDetailDTO dto) throws BusinessLogicException 
    {
        CompraEntity compra = dto.toEntity();
         return new CompraDetailDTO(obraLogic.addCompra(obraId, compra));
        
    }
    
    
    @PUT
    
    public CompraDetailDTO replaceCompra(@PathParam("id") Long obraId, CompraDetailDTO dto) throws BusinessLogicException
    {
        ObraEntity obra = obraLogic.findObra(obraId);
        CompraEntity viejo = obra.getCompra();
        CompraEntity nuevo = dto.toEntity();
        nuevo.setId(viejo.getId());
        nuevo.setObras(viejo.getObras());
        return new CompraDetailDTO(compraLogic.updateCompra(nuevo));
        
    
    }    
    
    @DELETE
    public void removeCompra(@PathParam("id") Long obraId) throws BusinessLogicException
    {
        
        ObraEntity obra = obraLogic.findObra(obraId);
        CompraEntity compra = obra.getCompra();
        obraLogic.delete(compra.getId());
            
        
    }
     
     
     
}
