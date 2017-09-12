/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author af.leon
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable {
    
    private String contenido;
    
    public String getContenido(){
        return contenido;
    }
    
    public void setContenido(String contenido){
        this.contenido = contenido;
    }
}
