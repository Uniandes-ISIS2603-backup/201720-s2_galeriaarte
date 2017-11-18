/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;

/**
 *
 * @author ma.abril
 */
public class PagoDTO {

    private Long id;

    private double total;

    private double impuesto;

    public PagoDTO() {
        //Constructor por defecto
    }

    public PagoDTO(PagoEntity pagoE) {
        if (pagoE != null) {
            this.id = pagoE.getId();
            this.total = pagoE.getTotal();
            this.impuesto = pagoE.getImpuesto();
        }
    }

    public PagoEntity toEntity() {
        PagoEntity pagoE = new PagoEntity();
        pagoE.setId(this.id);
        pagoE.setTotal(this.total);
        pagoE.setImpuesto(this.impuesto);

        return pagoE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

}
