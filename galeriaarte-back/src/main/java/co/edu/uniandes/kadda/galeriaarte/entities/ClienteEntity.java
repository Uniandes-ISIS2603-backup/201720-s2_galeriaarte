/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ks.estupinan
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable {

    private String tipoTarjeta;
    private int numTarjeta;
    
    
   @PodamExclude
   @OneToMany(mappedBy = "cliente")
   private List<CompraEntity> compra;
   
   @PodamExclude
   @ManyToOne
   private GaleriaEntity clienteGaleria;
      
   @PodamExclude
   @OneToMany(mappedBy = "clienteComentario")
   private List<ComentarioEntity> comentarios;
   
   @PodamExclude
   @OneToMany(mappedBy = "cliente")
   private List<ObraEntity> obra;
    
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
