/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;

/**
 *
 * @author ks.estupinan
 */
public class ComentarioDTO {

    private Long id;
    private String contenido;

    public ComentarioDTO() {
        //Constructor por defecto
    }

    public ComentarioDTO(ComentarioEntity entidad) {
        this.id = entidad.getId();
        this.contenido = entidad.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public ComentarioEntity toEntity() {
        ComentarioEntity entity = new ComentarioEntity();
        entity.setId(this.id);
        entity.setName(this.contenido);
        return entity;
    }
}
