/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;

/**
 *
 * @author jd.carrillor
 */
public class HojaVidaDTO
{
    private Long id;
    
    private String trayectoria;
    
    private String almaMater;
    
    private String nacionalidad;
    
     public HojaVidaDTO()
    {
        
        
        
    }
     
     
     
    
     
     
     
    public HojaVidaDTO(HojaVidaEntity hoja)
    {
        
       
        if(hoja!=null){ 
        this.id = hoja.getId();
        this.trayectoria = hoja.getTrayectoria();
        this.almaMater  =  hoja.getAlmaMater();
        this.nacionalidad = hoja.getNacionalidad();
        }
        
    }
    
    
     public HojaVidaEntity toEntity() {
        HojaVidaEntity entity = new HojaVidaEntity();
        entity.setId(this.getId());
        entity.setTrayectoria(this.getTrayectoria());
        entity.setAlmaMater(this.getAlmaMater());
        entity.setNacionalidad(this.getNacionalidad());
        
        
        
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
     * @return the trayectoria
     */
    public String getTrayectoria() {
        return trayectoria;
    }

    /**
     * @param trayectoria the trayectoria to set
     */
    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    /**
     * @return the almaMater
     */
    public String getAlmaMater() {
        return almaMater;
    }

    /**
     * @param almaMater the almaMater to set
     */
    public void setAlmaMater(String almaMater) {
        this.almaMater = almaMater;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
     
     
    
    
    
    
}
