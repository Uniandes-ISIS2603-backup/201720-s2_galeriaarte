/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import java.util.Date;

/**
 *
 * @author ma.abril
 */
public class CompraDTO {

    private Long id;
    private double valor;
    private Date fecha;

    public CompraDTO() {
        //Constructor por defecto 
    }

    public CompraDTO(CompraEntity compraE) {
        if (compraE != null) {
            this.id = compraE.getId();
            this.valor = compraE.getValor();
            this.fecha = compraE.getFecha();
        }
    }

    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the description to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the publishingdate to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CompraEntity toEntity() {
        CompraEntity compraE = new CompraEntity();
        compraE.setId(this.id);
        compraE.setValor(this.valor);
        compraE.setFecha(this.fecha);
        return compraE;
    }

}
