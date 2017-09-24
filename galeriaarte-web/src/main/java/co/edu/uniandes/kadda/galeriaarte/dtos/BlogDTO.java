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
    
    private String contenido;
    
    /**
     * Constructor por defecto
     */
    public BlogDTO(){
        
    }
    
    /**
     * Crea un objeto BlogDTO a partir de un objeto BlogEntity.
     *
     * @param entity Entidad BlogEntity desde la cual se va a crear el nuevo
     * objeto.
     * 
     */
    public BlogDTO(BlogEntity entity) {
            this.id = entity.getId();
            this.contenido = entity.getContenido();
    }
    
    /**
     * Convierte un objeto BlogDTO a BlogEntity.
     *
     * @return Nueva objeto BlogEntity.
     * 
     */
    public BlogEntity toEntity() {
        BlogEntity entity = new BlogEntity();
        entity.setId(this.id);
        entity.setContenido(this.contenido);
        return entity;
    }
    
    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el atributo contenido.
     *
     * @return atributo contenido.
     * 
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el valor del atributo contenido.
     *
     * @param contenido nuevo valor del atributo
     * 
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
