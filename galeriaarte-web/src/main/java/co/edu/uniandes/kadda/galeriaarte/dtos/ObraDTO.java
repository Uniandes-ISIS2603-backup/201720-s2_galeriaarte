/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;

/**
 *
 * @author jd.carrillor
 */
public class ObraDTO {

    private Long id;

    private String name;

    private String tipo;

    private int cantidad;

    private double valor;

    private String imagen;

    public ObraDTO() {
        //Constructor por defecto 
    }

    public ObraDTO(ObraEntity obra) {
        if (obra != null) {
            this.id = obra.getId();
            this.name = obra.getName();
            this.tipo = obra.getTipo();
            this.cantidad = obra.getCantidad();
            this.valor = obra.getValor();
            this.imagen = obra.getImagen();

        }
    }

    public ObraEntity toEntity() {
        ObraEntity entity = new ObraEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTipo(this.tipo);
        entity.setCantidad(this.cantidad);
        entity.setValor(this.valor);
        entity.setImagen(this.imagen);

        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
        this.name = nombre;
    }

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
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
