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
    
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ArtistaDetailDTO(ArtistaEntity entity) {
        super(entity);
        if (entity.getHojaVida()!= null) {
            this.hoja = new HojaVidaDTO(entity.getHojaVida());
        } else {
            entity.setHojaVida(null);
        }
        if (entity.getBlogs() != null) {
            blogs = new ArrayList<>();
            for (BlogEntity entityBlog : entity.getBlogs()) {
                blogs.add(new BlogDTO(entityBlog));
            }
        }
        if (entity.getObras() != null) {
            obras = new ArrayList<>();
            for (ObraEntity entityObra : entity.getObras()) {
                obras.add(new ObraDTO(entityObra));
            }

        }
    }
   
    @Override
    public ArtistaEntity toEntity() {
        ArtistaEntity artistaE=  super.toEntity();
        if (this.getHoja()!= null) {
            artistaE.setHojaVida(this.getHoja().toEntity());
        }
        if (getBlogs() != null) {
            List<BlogEntity> blogsEntity = new ArrayList<>();
            for (BlogDTO dtoReview : getBlogs()) {
                blogsEntity.add(dtoReview.toEntity());
            }
            artistaE.setBlogs(blogsEntity);
        }
        
        if (obras != null) {
            List<ObraEntity> obrasEntity = new ArrayList<>();
            for (ObraDTO dtoAuthor : obras) {
                obrasEntity.add(dtoAuthor.toEntity());
            }
            artistaE.setObras(obrasEntity);
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
