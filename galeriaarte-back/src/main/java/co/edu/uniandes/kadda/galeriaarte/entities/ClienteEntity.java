/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.List;
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

    public GaleriaEntity getClienteGaleria() {
        return clienteGaleria;
    }

    public void setClienteGaleria(GaleriaEntity clienteGaleria) {
        this.clienteGaleria = clienteGaleria;
    }

    public List<CompraEntity> getCompra() {
        return compra;
    }

    public void setCompra(List<CompraEntity> compra) {
        this.compra = compra;
    }

    public List<ObraEntity> getObra() {
        return obra;
    }

    public void setObra(List<ObraEntity> obra) {
        this.obra = obra;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public void setComentario(ComentarioEntity comentario) {
        this.comentarios.add(comentario);
    }

    public void setObra(ObraEntity obra) {
        this.obra.add(obra);
    }

    public void setCompra(CompraEntity compra) {
        this.compra.add(compra);
    }

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
