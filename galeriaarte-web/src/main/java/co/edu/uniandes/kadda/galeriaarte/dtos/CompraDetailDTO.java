/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ma.abril
 */
public class CompraDetailDTO extends CompraDTO {
    
   private ClienteDTO cliente;
    
    private List<ObraDTO> obras;
    
    private PagoDTO pago;
    
    public CompraDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CompraDetailDTO(CompraEntity entity) {
        super(entity);
        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        } else {
            entity.setCliente(null);
        }
        if (entity.getObras() != null) {
            obras = new ArrayList<>();
            for (ObraEntity entityReview : entity.getObras()) {
                obras.add(new ObraDTO(entityReview));
            }
        }
       if (entity.getPago() != null) {
            this.pago = new PagoDTO(entity.getPago());
        } else {
            entity.setPago(null);
        }
    }

    @Override
    public CompraEntity toEntity() {
        CompraEntity compraE = super.toEntity();
        if(this.getCliente() != null) {
            compraE.setCliente(this.getCliente().toEntity());
        }
        if (obras != null) {
            List<ObraEntity> obrasEntity = new ArrayList<>();
            for (ObraDTO dtoObra : getObras()) {
                obrasEntity.add(dtoObra.toEntity());
            }
            compraE.setObras(obrasEntity);
        }
        if(getPago() != null) {
            compraE.setPago(this.getPago().toEntity());
        }
        return compraE;
    }

    /**
     * @return obras
     */
    public List<ObraDTO> getObras() {
        return obras;
    }

    /**
     * @param obras
     */
    public void setObras(List<ObraDTO> obras) {
        this.obras = obras;
    }

    /**
     * @return el pago
     */
    public PagoDTO getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
 
    public ClienteDTO getCliente() {
        return cliente;
    }
}
