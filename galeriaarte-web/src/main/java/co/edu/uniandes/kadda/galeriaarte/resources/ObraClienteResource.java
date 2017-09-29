/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.ClienteDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.dtos.CompraDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ClienteLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
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

@Path("obras/{id: \\d+}/cliente")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObraClienteResource
{
    
 @Inject
ObraLogic obraLogic;
 
 @Inject
ClienteLogic clienteLogic;
 
 
 
 @GET 
    public  ClienteDetailDTO getArtista (@PathParam("id") Long obra) throws BusinessLogicException
    {
          if (obraLogic.findObra(obra)!=null) 
        {
        ObraEntity ent = obraLogic.findObra(obra);
        
        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + obra);
        }
            ClienteEntity cliente =ent.getCliente();
            return new ClienteDetailDTO(cliente);
    
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
    }
    
    
    @POST   
    public ClienteDetailDTO addCliente(@PathParam("id") Long obraId, ClienteDetailDTO dto) throws BusinessLogicException 
    {
        ClienteEntity cliente = dto.toEntity();
         return new ClienteDetailDTO(obraLogic.addCliente(obraId, cliente));
        
    }
    
    
    @PUT
    public ClienteDetailDTO replaceCliente(@PathParam("id") Long obraId, ClienteDetailDTO dto) throws BusinessLogicException
    {
        ObraEntity obra = obraLogic.findObra(obraId);
        ClienteEntity viejo = obra.getCliente();
        ClienteEntity nuevo = dto.toEntity();
        nuevo.setId(viejo.getId());
        nuevo.setObra(viejo.getObra());
        return new ClienteDetailDTO(clienteLogic.updateCliente(nuevo));
        
    
    }    
    
    
    @DELETE
    public void removeCliente(@PathParam("id") Long obraId) throws BusinessLogicException
    {
        
        ObraEntity obra = obraLogic.findObra(obraId);
        ClienteEntity cliente = obra.getCliente();
        obraLogic.delete(cliente.getId());
            
        
    }
    
    
    
}
