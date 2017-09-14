

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author Daniel Perilla
 */

@Entity

public class CatalogoEntity extends BaseEntity implements Serializable
{
    private Long id;
    private String categoria;
  //  private ArrayList<ObraEntity> obras;
    
    
    /**
     *
     * @return
     */
    @Override
    public Long getId()
    {
        return id;
    }
    
    @Override
    public void setId(Long pId)
    {
        this.id = pId;
    }
    
    public String getCategoria()
    {
        return categoria;
    }
    
    public void setCategoria(String pCategoria)
    {
        this.categoria = pCategoria;
    }
    
    //public ArrayList<ObraEntity> getObras()
    //{
    //    return obras;
    //}
    
   // public void setObras(ArrayList<ObraEntity> pObras)
   // {
   //     this.obras = pObras;
   // }
    
    
    
}
