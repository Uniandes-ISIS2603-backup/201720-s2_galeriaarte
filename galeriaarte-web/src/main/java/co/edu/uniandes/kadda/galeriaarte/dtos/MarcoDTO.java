/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;

/**
 *
 * @author af.leon
 */
public class MarcoDTO {
    
    private Long id;
    
    private double valor;
    
    private String material;
    
    private double ancho;
    
    private double alto;
    
    private String image;
    
    private String name;
    
    /**
     * Constructor por defecto
     */
    public MarcoDTO(){
        //Constructor por defecto
    }
    
    /**
     * Crea un objeto MarcoDTO a partir de un objeto MarcoEntity.
     * @param entity Entidad MarcoEntity desde la cual se va a crear el nuevo
     * objeto.
     */
    public MarcoDTO(MarcoEntity entity) 
    {
        if(entity!=null){
            this.id = entity.getId();
            this.valor = entity.getValor();
            this.material = entity.getMaterial();
            this.ancho = entity.getAncho();
            this.alto = entity.getAlto();
            this.image = entity.getImage();
            this.name = entity.getName();
        }
    }
    
    /**
     * Convierte un objeto MarcoDTO a MarcoEntity.
     * @return Nueva objeto MarcoEntity.
     */
    public MarcoEntity toEntity() {
        MarcoEntity entity = new MarcoEntity();
        entity.setId(this.id);
        entity.setValor(this.valor);
        entity.setMaterial(this.material);
        entity.setAncho(this.ancho);
        entity.setAlto(this.alto);
        entity.setImage(this.image);
        entity.setName(this.name);
        return entity;
    }
    
    /**
     * Obtiene el atributo id.
     * @return atributo id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     * @param id nuevo valor del atributo
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el atributo valor.
     * @return atributo valor.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Establece el valor del atributo valor.
     * @param valor nuevo valor del atributo
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /**
     * Obtiene el atributo material.
     * @return atributo material.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Establece el valor del atributo material.
     * @param material nuevo valor del atributo
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * Obtiene el atributo ancho.
     * @return atributo ancho.
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * Establece el valor del atributo ancho.
     * @param ancho nuevo valor del atributo
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    
    /**
     * Obtiene el atributo alto.
     * @return atributo alto.
     */
    public double getAlto() {
        return alto;
    }

    /**
     * Establece el valor del atributo alto.
     * @param alto nuevo valor del atributo
     */
    public void setAlto(double alto) {
        this.alto = alto;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
