/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;

/**
 *
 * @author jd.carrillor
 */
public class ObraDetailDTO extends ObraDTO
{
     public ObraDetailDTO()
    {
        
    }
    
    public ObraDetailDTO(ObraEntity entity)
    {
        super(entity);
    }
    @Override
    
    public ObraEntity toEntity() {
        ObraEntity obraE = super.toEntity();
        return obraE;
    }
    
}
