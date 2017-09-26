/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author af.leon
 */
@Entity
public class MarcoEntity extends BaseEntity implements Serializable{
    
    private double valor;
    
    private String material;
    
    private double ancho;
    
    private double alto;
    
    @PodamExclude
    @OneToOne
    private ObraEntity obra;
    
    public double getValor(){
       return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public String getMaterial(){
        return material;
    }
    
    public void setMaterial(String material){
        this.material = material;
    }
    
    public double getAncho(){
        return ancho;
    }
    
    public void setAncho(double ancho){
        this.ancho = ancho;
    }
    
    public double getAlto(){
        return alto;
    }
    
    public void setAlto(double alto){
        this.alto = alto;
    }
    
    public ObraEntity getObra(){
        return obra;
    }
    
    public void setObra(ObraEntity obra){
        this.obra = obra;
    }
    
    
            
}
