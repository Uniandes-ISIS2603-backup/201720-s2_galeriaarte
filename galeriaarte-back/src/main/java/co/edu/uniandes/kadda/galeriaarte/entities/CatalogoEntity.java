/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Perilla
 */
@Entity
public class CatalogoEntity extends BaseEntity implements Serializable
{
    private String categoria;
    @PodamExclude
    @OneToMany(mappedBy = "catalogo")
    private List<ObraEntity> obras;
    @PodamExclude
    @ManyToOne
    private GaleriaEntity galeria;

    public String getCategoria()
    {
        return categoria;
    }
    public void setCategoria(String pCategoria)
    {
        this.categoria = pCategoria;
    }
    public List<ObraEntity> getObras()
    {
        return obras;
    }
    public void setObras(List<ObraEntity> pObras)
    {
        this.obras = pObras;
    }
    public GaleriaEntity getGaleria()
    {
        return galeria;
    }
    public void setGaleria(GaleriaEntity pGaleria)
    {
        this.galeria = pGaleria;
    }
    
}
