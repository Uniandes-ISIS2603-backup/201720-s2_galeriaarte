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
public class ObraDTO
{
 
    private Long id;
    
    private String nombre;
    
    private String tipo;
    
    private int cantidad;
    
    private double valor;
    
    
    public ObraDTO()
    {
        
    }
    
    public ObraDTO(ObraEntity obra)
    {
        if(obra!=null){
        this.id = obra.getId();
        this.nombre = obra.getName();
        this.tipo  =  obra.getTipo();
        this.cantidad = obra.getCantidad();
        this.valor =  obra.getValor();
        
        
        }
    }
    
    
     public ObraEntity toEntity() {
        ObraEntity entity = new ObraEntity();
        entity.setId(this.getId());
        entity.setName(this.getNombre());
        entity.setTipo(this.getTipo());
        entity.setCantidad(this.getCantidad());
        entity.setValor(this.getValor());
        
        
        
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
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    
    
}
