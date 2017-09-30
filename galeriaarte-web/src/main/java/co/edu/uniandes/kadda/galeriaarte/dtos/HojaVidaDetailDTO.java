/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;

/**
 *
 * @author jd.carrillor
 */
public class HojaVidaDetailDTO extends HojaVidaDTO
{
    private ArtistaDTO artista;
    
    public HojaVidaDetailDTO()
    {
        super();
    }
    
    public HojaVidaDetailDTO(HojaVidaEntity entity)
    {
        super(entity);
        if (entity.getArtista() != null) {
            this.artista = new ArtistaDTO(entity.getArtista());
        }
        
    }
    @Override
    
    public HojaVidaEntity toEntity() {
        HojaVidaEntity artistaE = super.toEntity();
        
          if (this.getArtista() != null) {
            artistaE.setArtista(this.getArtista().toEntity());
        }
        return artistaE;
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
