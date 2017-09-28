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
    
    private ArtistaDTO artista;
    
     public ObraDetailDTO()
    {
        
    }
    
    public ObraDetailDTO(ObraEntity entity)
    {
         super(entity);
        if (entity.getArtista() != null) {
            this.artista = new ArtistaDTO(entity.getArtista());
        } 
    }
    @Override
    
    public ObraEntity toEntity() {
        ObraEntity obraE = super.toEntity();
        if (this.getArtista() != null) {
            obraE.setArtista(this.getArtista().toEntity());
        }
        
        
        return obraE;
    }

    /**
     * @return the artista
     */
    public ArtistaDTO getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }
    
}
