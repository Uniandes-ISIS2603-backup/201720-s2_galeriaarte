/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author jd.carrillor
 */






@Entity

public class ArtistaEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToMany(mappedBy = "artista")
    private List<ObraEntity> obras = new ArrayList<ObraEntity>();
    @PodamExclude
    @ManyToOne
    private GaleriaEntity galeria;
    @PodamExclude
    @OneToOne(mappedBy = "artista", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private HojaVidaEntity hojaVida;
    @PodamExclude
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogEntity> blogs = new ArrayList<BlogEntity>();

    /**
     * @return the obras
     */
    public List<ObraEntity> getObras() {
        return obras;
    }

    /**
     * @param obras the obras to set
     */
    public void setObras(List<ObraEntity> obras) {
        this.obras = obras;
    }

    /**
     * @return the galeria
     */
    public GaleriaEntity getGaleria() {
        return galeria;
    }

    /**
     * @param galeria the galeria to set
     */
    public void setGaleria(GaleriaEntity galeria) {
        this.galeria = galeria;
    }

    /**
     * @return the hojaVida
     */
    public HojaVidaEntity getHojaVida() {
        return hojaVida;
    }

    /**
     * @param hojaVida the hojaVida to set
     */
    public void setHojaVida(HojaVidaEntity hojaVida) {
        this.hojaVida = hojaVida;
    }

    /**
     * @return the blogs
     */
    public List<BlogEntity> getBlogs() {
        return blogs;
    }

    /**
     * @param blogs the reviews to set
     */
    public void setBlogs(List<BlogEntity> blogs) {
        this.blogs = blogs;
    }
  
}
