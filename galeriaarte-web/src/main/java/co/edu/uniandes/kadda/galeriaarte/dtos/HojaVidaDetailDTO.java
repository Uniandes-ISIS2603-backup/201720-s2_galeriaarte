/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;

/**
 *
 * @author jd.carrillor
 */
public class HojaVidaDetailDTO extends HojaVidaDTO
{
    public HojaVidaDetailDTO()
    {
        
    }
    
    public HojaVidaDetailDTO(HojaVidaEntity entity)
    {
        super(entity);
    }
    @Override
    
    public HojaVidaEntity toEntity() {
        HojaVidaEntity artistaE = super.toEntity();
        return artistaE;
    }
    
    
}
