/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;

/**
 *
 * @author jd.carrillor
 */
public class ArtistaDetailDTO extends ArtistaDTO
{
 
    public ArtistaDetailDTO()
    {
        
    }
    
    public ArtistaDetailDTO(ArtistaEntity entity)
    {
        super(entity);
    }
    @Override
    
    public ArtistaEntity toEntity() {
        ArtistaEntity artistaE = super.toEntity();
        return artistaE;
    }
}
