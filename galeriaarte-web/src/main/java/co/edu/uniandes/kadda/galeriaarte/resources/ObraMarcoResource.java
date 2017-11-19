/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.MarcoDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.MarcoLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.carrillor
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("obras/{id: \\d+}/marco")
@RequestScoped
public class ObraMarcoResource {

    @Inject
    ObraLogic obraLogic;
    @Inject
    MarcoLogic marcoLogic;

    @GET
    public MarcoDetailDTO getGaleria(@PathParam("id") Long obraId) throws BusinessLogicException {
        if (obraLogic.findObra(obraId) != null) {
            ObraEntity ent = obraLogic.findObra(obraId);

            if (ent == null) {
                throw new BusinessLogicException("No existe el proveedor con id " + obraId);
            }
            MarcoEntity marco = ent.getMarco();
            return new MarcoDetailDTO(marco);

        } else {
            throw new BusinessLogicException("error");
        }

    }

    @POST
    public MarcoDetailDTO addMarco(@PathParam("id") Long obraId, MarcoDetailDTO dto) throws BusinessLogicException {
        MarcoEntity gal = dto.toEntity();
        return new MarcoDetailDTO(obraLogic.addMarco(obraId, gal));

    }

    @PUT
    public MarcoDetailDTO replaceMarco(@PathParam("id") Long marcoId, MarcoDetailDTO dto) throws BusinessLogicException {

        ObraEntity obra = obraLogic.findObra(marcoId);
        MarcoEntity oldObra = obra.getMarco();
        MarcoEntity obraNueva = dto.toEntity();
        obraNueva.setId(oldObra.getId());
        obraNueva.setObra(obra);

        return new MarcoDetailDTO(marcoLogic.updateMarco(obraNueva));

    }

    @DELETE
    public void removeMarco(@PathParam("id") Long obraId) throws BusinessLogicException {
        ObraEntity obra = obraLogic.findObra(obraId);
        MarcoEntity marcoEliminar = obra.getMarco();

        marcoLogic.deleteMarco(marcoEliminar.getId());
    }

}
