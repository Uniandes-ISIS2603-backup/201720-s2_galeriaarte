/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import java.util.List;

/**
 *
 * @author Daniel Perilla
 */
public class CatalogoDTO 
{
    
    private Long id;
    private String categoria;
    
    /**
     * Relación con las obras
     */
    
    private List<ObraDTO> obras;
   
    /**
     * Constructor por defecto
     */
    public CatalogoDTO(){
    }
    
     public CatalogoDTO(CatalogoEntity catalogo)
    {
        
        this.id = catalogo.getId();
        this.categoria = catalogo.getCategoria();
        
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
     * @return the name
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param name the name to set
     */
    public void setCategoria(String pCategoria) {
        this.categoria = pCategoria;
    }
    
    /**
     * 
     * @return las obras relacionadas con el catálogo
     */
    
    public List<ObraDTO> getObras()
    {
        return obras;
    }
    
    /**
     * 
     * @return 
     */
    
    public void setObras(List<ObraDTO> pObras)
    {
        this.obras = pObras;
    }
    
    public CatalogoEntity toEntity()
    {
        CatalogoEntity catalogo = new CatalogoEntity();
        catalogo.setId(this.id);
        catalogo.setCategoria(this.categoria);
        return catalogo;
    }
    
}
