/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jd.carrillor
 */
public class ArtistaDetailDTO extends ArtistaDTO
{
    private List<ObraDTO> obras;
    private GaleriaDTO galeria;
    private List<BlogDTO> blogs;
    private HojaVidaDTO hoja;
 
    public ArtistaDetailDTO()
    {
        super();
    }
    
    public ArtistaDetailDTO(ArtistaEntity entity)
    {
        super(entity);
        if (entity != null) {
            obras = new ArrayList<>();
            for (ObraEntity entityBooks : entity.getObras()) {
                obras.add(new ObraDTO(entityBooks));
            }
        }
         if (entity.getHojaVida() != null) {
            this.hoja = new HojaVidaDTO(entity.getHojaVida());
        }
          if (entity.getGaleria() != null) {
            this.galeria = new GaleriaDTO(entity.getGaleria());
        }
          
            
            if (entity != null) {
            blogs = new ArrayList<>();
            for (BlogEntity entityBlogs: entity.getBlogs()) {
                blogs.add(new BlogDTO(entityBlogs));
            }
        }
            
           
            
            
           
            
            
            
            

        }
    
    @Override
    
    public ArtistaEntity toEntity() {
        ArtistaEntity artistaE = super.toEntity();
        if (getObras() != null) {
            List<ObraEntity> obrasEntity = new ArrayList<>();
            for (ObraDTO dtoBook : getObras()) {
                obrasEntity.add(dtoBook.toEntity());
            }
            artistaE.setObras(obrasEntity);
        }
        if (getBlogs()!= null) {
            List<BlogEntity> blogEntity = new ArrayList<>();
            for (BlogDTO dtoBlog : getBlogs()) {
                blogEntity.add(dtoBlog.toEntity());
            }
            artistaE.setBlogs(blogEntity);
        }
        
        if (this.getGaleria() != null) {
            artistaE.setGaleria(this.getGaleria().toEntity());
        }
        
        if (this.getHoja() != null) {
            artistaE.setHojaVida(this.getHoja().toEntity());
        }
        
        
        return artistaE;
    }

    /**
     * @return the obras
     */
    public List<ObraDTO> getObras() {
        return obras;
    }

    /**
     * @param obras the obras to set
     */
    public void setObras(List<ObraDTO> obras) {
        this.obras = obras;
    }

    /**
     * @return the galeria
     */
    public GaleriaDTO getGaleria() {
        return galeria;
    }

    /**
     * @param galeria the galeria to set
     */
    public void setGaleria(GaleriaDTO galeria) {
        this.galeria = galeria;
    }

    /**
     * @return the blogs
     */
    public List<BlogDTO> getBlogs() {
        return blogs;
    }

    /**
     * @param blogs the blogs to set
     */
    public void setBlogs(List<BlogDTO> blogs) {
        this.blogs = blogs;
    }

    /**
     * @return the hoja
     */
    public HojaVidaDTO getHoja() {
        return hoja;
    }

    /**
     * @param hoja the hoja to set
     */
    public void setHoja(HojaVidaDTO hoja) {
        this.hoja = hoja;
    }
}
