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
public class BlogDTO {
    
    private Long id;
    private String name;
    private String contenido;
    
    /**
     * Constructor por defecto
     */
    public BlogDTO() {
    }

    public BlogDTO(BlogEntity entity) {

        this.id = entity.getId();
        this.name = entity.getName();
        this.contenido = entity.getContenido();
    }

    public BlogEntity toEntity() {
        BlogEntity entity = new BlogEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setContenido(this.contenido);
        return entity;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
