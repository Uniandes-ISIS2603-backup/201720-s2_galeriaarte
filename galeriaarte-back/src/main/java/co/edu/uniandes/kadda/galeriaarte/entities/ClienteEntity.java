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

    /**
     * @return GaleriaEntity
     */
    public GaleriaEntity getClienteGaleria() {
        return clienteGaleria;
    }

    /**
     * @param clienteGaleria
     */
    public void setClienteGaleria(GaleriaEntity clienteGaleria) {
        this.clienteGaleria = clienteGaleria;
    }

    /**
     * @return CompraEntity
     */
    public List<CompraEntity> getCompra() {
        return compra;
    }

    /**
     * @param compra
     */
    public void setCompra(List<CompraEntity> compra) {
        this.compra = compra;
    }

    /**
     * @return ObraEntity
     */
    public List<ObraEntity> getObra() {
        return obra;
    }

    /**
     * @param obra
     */
    public void setObra(List<ObraEntity> obra) {
        this.obra = obra;
    }

    /**
     * @return ComentarioEntity
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @param comentario
     */
    public void setComentario(ComentarioEntity comentario) {
        this.comentarios.add(comentario);
    }

    /**
     * @param obra
     */
    public void setObra(ObraEntity obra) {
        this.obra.add(obra);
    }

    /**
     * @param compra
     */
    public void setCompra(CompraEntity compra) {
        this.compra.add(compra);
    }

    /**
     * @return String
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * @param nTipoTarjeta
     */
    public void setTipoTarjeta(String nTipoTarjeta) {
        this.tipoTarjeta = nTipoTarjeta;
    }

    /**
     * @return Integer
     */
    public int getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * @param nNumTarjeta
     */
    public void setNumtarjeta(int nNumTarjeta) {
        this.numTarjeta = nNumTarjeta;
    }

    @Override
     public boolean equals(Object obj) {
        if (obj instanceof  ClienteEntity)
            return super.equals(obj);
        return false;
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.tipoTarjeta != null ? this.tipoTarjeta.hashCode() : 0);
        hash = 67 * hash + this.numTarjeta;
        return hash;
    }
    
}
