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
 * @author ks.estupinan
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable {

    private String tipoTarjeta;
    private int numTarjeta;

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String nTipoTarjeta) {
        this.tipoTarjeta = nTipoTarjeta;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumtarjeta(int nNumTarjeta) {
        this.numTarjeta = nNumTarjeta;
    }
}
