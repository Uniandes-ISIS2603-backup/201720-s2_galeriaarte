/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;


/**
 *
 * @author af.leon
 */
public class MarcoDetailDTO extends MarcoDTO {
    
    private ObraDTO obra;
    
    public MarcoDetailDTO(){
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public MarcoDetailDTO(MarcoEntity entity) {
        super(entity);
        if(entity!=null){
        if (entity.getObra()!= null) {
            this.obra = new ObraDTO(entity.getObra());
        } else {
            entity.setObra(null);
        } 
        }
    }
    
    @Override
    public MarcoEntity toEntity() {
        MarcoEntity entity = super.toEntity();
        if (this.getObra() != null) {
            entity.setObra(this.getObra().toEntity());
        }
        return entity;
    }
    
    public void setObra(ObraDTO obra) {
        this.obra = obra;
    }

    public ObraDTO getObra() {
        return obra;
    }
}
