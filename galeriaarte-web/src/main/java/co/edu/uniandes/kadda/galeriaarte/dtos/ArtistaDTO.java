/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;



/**
 *
 * @author jd.carrillor
 */
public class ArtistaDTO 
{
    private Long id;
    private String name;
    private String imagen;
    
     /**
     * Constructor por defecto
     */
    public ArtistaDTO() {
    }
    
    
    

    public ArtistaDTO(ArtistaEntity artistaE) {
        if (artistaE != null) {
            this.id = artistaE.getId();
            this.name = artistaE.getName();
            this.imagen = artistaE.getImagen();
        }
    }

    public ArtistaEntity toEntity() {

        ArtistaEntity ArtistaE = new ArtistaEntity();
        ArtistaE.setId(this.id);
        ArtistaE.setName(this.name);
        ArtistaE.setImagen(this.imagen);
    
        return ArtistaE;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
