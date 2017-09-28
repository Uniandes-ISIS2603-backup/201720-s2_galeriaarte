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
public class ArtistaDTO 
{
    private Long id;
    
    private String name;
    
    public ArtistaDTO()
    {
        
    }
    
    public ArtistaDTO(ArtistaEntity artista)
    {
        
        this.id = artista.getId();
        this.name = artista.getName();
        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
        this.name = nombre;
    }
    
     public ArtistaEntity toEntity() {
        ArtistaEntity entity = new ArtistaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        
        return entity;
    }
    
    
}
