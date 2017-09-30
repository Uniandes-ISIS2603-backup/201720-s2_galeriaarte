    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.resources;

import co.edu.uniandes.kadda.galeriaarte.dtos.BlogDetailDTO;
import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.BlogLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author jd.carrillor
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artistas/{id: \\d+}/blogs")
@RequestScoped
public class ArtistaBlogResource 
{
   
  @Inject
  ArtistaLogic artistaLogic;
  
  @Inject
  BlogLogic blogLogic;
  
  
  @GET 
    public  List<BlogDetailDTO> getObras (@PathParam("id") Long artista) throws BusinessLogicException
    {
          if (artistaLogic.findArtista(artista)!=null) 
        {
            ArtistaEntity ent = artistaLogic.findArtista(artista);
            if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + artista);
        List<BlogDetailDTO> list = new ArrayList();
        for (BlogEntity fbEntity : artistaLogic.listBlogs(artista))
            list.add(new BlogDetailDTO(fbEntity));
        return list;
        }
          else
          {
              throw new BusinessLogicException("error");
          }
        
        
    }
    
    @GET
    @Path(("/{idBlog:\\d+}"))
    public BlogDetailDTO getBlog ( @PathParam("id") Long idartista, @PathParam("idBlog") Long idBlog) throws BusinessLogicException
    {
        ArtistaEntity ent = artistaLogic.findArtista(idartista);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + idartista);
        BlogDetailDTO fbDetail = null;
        for (BlogEntity fb : ent.getBlogs())
            if (fb.getId().equals(idBlog)) fbDetail = (new BlogDetailDTO(fb));
        if (fbDetail == null) throw new BusinessLogicException("No existe la obra con id " + idBlog + " del proveedor con id " + idartista);
        return fbDetail;
    }
    
 
    @POST   
    public BlogDetailDTO addBlog(@PathParam("id") Long artistaId, BlogDetailDTO dto) throws BusinessLogicException 
    {
        BlogEntity blog = dto.toEntity();
        return new BlogDetailDTO(artistaLogic.addBlog(artistaId, blog));
        
    }
    
    
    @PUT
    @Path(("/{idBlog:\\d+}"))
    public BlogDetailDTO replaceBlog(@PathParam("id") Long artistaId, BlogDetailDTO dto, @PathParam(("idBlog"))Long idBlog) throws BusinessLogicException
    {
        
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        List<BlogEntity> blogs = artista.getBlogs();
        for(int i =0; i<blogs.size();i++)
        {
            BlogEntity actual = blogs.get(i);
            if (actual.getId().equals(idBlog))
            {
             BlogEntity mod = dto.toEntity();
             mod.setId(idBlog);
             mod.setArtista(artista);
             return new BlogDetailDTO(blogLogic.updateBlog(mod));
            }
        }
            
      return null;
    
    }
    @DELETE
    @Path(("/{idBlog:\\d+}"))
    public void removeBlog(@PathParam("id") Long artistaId, @PathParam(("idBlog"))Long idBlog) throws BusinessLogicException
    {
        ArtistaEntity artista = artistaLogic.findArtista(artistaId);
        List<BlogEntity> blogsArtista =artista.getBlogs();
        for(int i =0;i<blogsArtista.size();i++)
        {
            BlogEntity obraActual = blogsArtista.get(i);
            if(obraActual.getId().equals(idBlog))
            {
                blogLogic.deleteBlog(idBlog);
            }
        }
    }
    
    
    
    
    
}
