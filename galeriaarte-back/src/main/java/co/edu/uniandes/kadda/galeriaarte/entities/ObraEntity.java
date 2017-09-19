/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



/**
 *
 * @author jd.carrillor
 */
@Entity
public class ObraEntity extends BaseEntity implements Serializable
{

        
    private String tipo;
    
    private int cantidad;
    
    private double valor;
    
    @ManyToOne
    private ArtistaEntity artista;
    
    @ManyToOne
    private CatalogoEntity catalogo;
    
    @OneToMany(mappedBy = "obra")
    private List<ComentarioEntity> comentarios = new ArrayList<ComentarioEntity>();
    
    @ManyToOne
    private ClienteEntity cliente;
    
    @ManyToOne
    private CompraEntity compra;
    
    @OneToOne(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private MarcoEntity marco;
    
    

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the artista
     */
    public ArtistaEntity getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(ArtistaEntity artista) {
        this.artista = artista;
    }

    /**
     * @return the catalogo
     */
    public CatalogoEntity getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(CatalogoEntity catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the compra
     */
    public CompraEntity getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(CompraEntity compra) {
        this.compra = compra;
    }

    /**
     * @return the marco
     */
    public MarcoEntity getMarco() {
        return marco;
    }

    /**
     * @param marco the marco to set
     */
    public void setMarco(MarcoEntity marco) {
        this.marco = marco;
    }
    
   
}
