/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author af.leon
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable {
    
    private String contenido;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private ArtistaEntity artista;
    
    public String getContenido(){
        return contenido;
    }
    
    public void setContenido(String contenido){
        this.contenido = contenido;
    }
    
    public ArtistaEntity getArtista(){
        return artista;
    }
    
    public void setArtista(ArtistaEntity artista){
        this.artista = artista;
    }
}
