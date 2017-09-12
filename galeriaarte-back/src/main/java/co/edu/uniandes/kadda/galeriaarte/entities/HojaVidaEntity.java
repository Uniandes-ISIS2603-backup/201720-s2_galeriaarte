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
 * @author jd.carrillor
 */

@Entity
public class HojaVidaEntity extends BaseEntity implements Serializable
{
    private String trayectoria;
    
    private String almaMater;
    
    private String nacionalidad;

    /**
     * @return the trayectoria
     */
    public String getTrayectoria() {
        return trayectoria;
    }

    /**
     * @param trayectoria the trayectoria to set
     */
    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    /**
     * @return the almaMater
     */
    public String getAlmaMater() {
        return almaMater;
    }

    /**
     * @param almaMater the almaMater to set
     */
    public void setAlmaMater(String almaMater) {
        this.almaMater = almaMater;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
}
