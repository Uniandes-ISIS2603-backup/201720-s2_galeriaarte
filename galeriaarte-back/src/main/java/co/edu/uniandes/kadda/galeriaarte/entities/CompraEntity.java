/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ma.abril
 */
@Entity
public class CompraEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToOne(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private PagoEntity pago; 
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    @PodamExclude
    @OneToMany(mappedBy = "compra")
    private List<ObraEntity> obras = new ArrayList<ObraEntity>();
            
    private double valor;
    @Temporal(TemporalType.DATE)
    private Date fecha;

   
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ObraEntity> getObras() {
        return obras;
    }

    public void setObras(List<ObraEntity> obras) {
        this.obras = obras;
    }
    
    
}
