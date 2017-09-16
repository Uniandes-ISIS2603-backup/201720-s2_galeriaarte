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
public class MarcoEntity extends BaseEntity implements Serializable{
    
    private double valor;
    
    private ObraEntity obra;
    
    public double getValor(){
       return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public ObraEntity getObra(){
        return obra;
    }
    
    public void setObra(ObraEntity obra){
        this.obra = obra;
    }
    
    
            
}
