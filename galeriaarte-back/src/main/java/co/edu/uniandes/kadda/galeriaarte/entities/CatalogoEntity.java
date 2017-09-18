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

/**
 *
 * @author Daniel Perilla
 */
@Entity
public class CatalogoEntity extends BaseEntity implements Serializable
{
//    @Id = id
//    private Long id;
    private String categoria;
    
//    @OneToMany(mappedBy = "obrasCatalogo")
//    private List<ObraEntity> obras;
//    
//    @ManyToOne
//    private GaleriaEntity galeria;

//    @Override
//    public Long getId()
//    {
//        return id;
//    }
//    /**
//     *
//     * @param pId
//     */
//    @Override
//    public void setId(Long pId)
//    {
//        this.id = pId;
//    }
    public String getCategoria()
    {
        return categoria;
    }
    public void setCategoria(String pCategoria)
    {
        this.categoria = pCategoria;
    }
//    public List<ObraEntity> getObras()
//    {
//        return obras;
//    }
//    public void setObras(List<ObraEntity> pObras)
//    {
//        this.obras = pObras;
//    }
//    public GaleriaEntity getGaleria()
//    {
//        return galeria;
//    }
//    public void setGaleria(GaleriaEntity pGaleria)
//    {
//        this.galeria = pGaleria;
//    }
    
}
