/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;

/**
 *
 * @author ks.estupinan
 */
public class ComentarioDetailDTO extends ComentarioDTO
{
        /*
    * Relación a una editorial
     */
    private ClienteDTO cliente;

    public ComentarioDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ComentarioDetailDTO(ComentarioEntity entity) {
        super(entity);
        if (entity.getClienteComentario()!= null) {
            this.cliente = new ClienteDTO(entity.getClienteComentario());
        } else {
            entity.setClienteComentario(null);
        }                
    }

    @Override
    public ComentarioEntity toEntity() {
        ComentarioEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setClienteComentario(this.getCliente().toEntity());
        }
        return entity;
    }
    
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }
}
