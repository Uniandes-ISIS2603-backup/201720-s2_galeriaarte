/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;



/**
 *
 * @author af.leon
 */
public class BlogDetailDTO extends BlogDTO {
    
    private ArtistaDTO artista;
    
    public BlogDetailDTO(){
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public BlogDetailDTO(BlogEntity entity) {
        super(entity);
        if (entity.getArtista()!= null) {
            this.artista = new ArtistaDTO(entity.getArtista());
        } else {
            entity.setArtista(null);
        }                
    }
    
    @Override
    public BlogEntity toEntity() {
        BlogEntity entity = super.toEntity();
        if (this.getArtista() != null) {
            entity.setArtista(this.getArtista().toEntity());
        }
        return entity;
    }
    
    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }

    public ArtistaDTO getArtista() {
        return artista;
    }
}
