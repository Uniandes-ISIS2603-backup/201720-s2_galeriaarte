/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jd.carrillor
 */
public class ObraDetailDTO extends ObraDTO {

    private ArtistaDTO artista;

    private MarcoDTO marco;
    
    private CompraDTO compra;
    
    private ClienteDTO cliente;
    
    private List<ComentarioDTO> comentarios;
    
    private CatalogoDTO catalogo;
    
    
    public ObraDetailDTO()
    {
        

    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;

    }


    public ObraDetailDTO(ObraEntity entity) {
        super(entity);
        if (entity.getArtista() != null) {
            this.artista = new ArtistaDTO(entity.getArtista());
        }

       if (entity.getMarco()!= null) {
            this.marco = new MarcoDTO(entity.getMarco());
        }
       if (entity.getCompra() != null) {
            this.compra = new CompraDTO(entity.getCompra());
        }
       if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        } 
       
       if (entity.getComentarios() != null) {
            comentarios = new ArrayList<>();
                for (ComentarioEntity entityComentario : entity.getComentarios()) {
                    comentarios.add(new ComentarioDTO(entityComentario))    ;
        }
       }
       if (entity.getCatalogo() != null) {
            this.catalogo = new CatalogoDTO(entity.getCatalogo());
        }          

        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        }

    }

    @Override

    public ObraEntity toEntity() {
        ObraEntity obraE = super.toEntity();
        if (this.getArtista() != null) {
            obraE.setArtista(this.getArtista().toEntity());
        }

        
         if (this.getCatalogo() != null) {
            obraE.setCatalogo(this.getCatalogo().toEntity());
        }
         
          if (this.getCliente() != null) {
            obraE.setCliente(this.getCliente().toEntity());
        }
          
          if (this.getComentarios() != null) {
            List<ComentarioEntity> comentarioEntity = new ArrayList<>();
            for (ComentarioDTO dtoComentario : this.getComentarios()) {
                comentarioEntity.add(dtoComentario.toEntity());
            }
            obraE.setComentarios(comentarioEntity);
        }
          
         if (this.getCompra() != null) {
            obraE.setCompra(this.getCompra().toEntity());
        }  
         
          if (this.getMarco() != null) {
            obraE.setMarco(this.getMarco().toEntity());
        }
        
        

        if (this.getCliente() != null) {
            obraE.setCliente(this.getCliente().toEntity());
        }


        return obraE;
    }

    /**
     * @return the artista
     */
    public ArtistaDTO getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }


    /**
     * @return the marco
     */
    public MarcoDTO getMarco() {
        return marco;
    }

    /**
     * @param marco the marco to set
     */
    public void setMarco(MarcoDTO marco) {
        this.marco = marco;
    }

    /**
     * @return the compra
     */
    public CompraDTO getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(CompraDTO compra) {
        this.compra = compra;
    }

   
   

    /**
     * @return the comentarios
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the catalogo
     */
    public CatalogoDTO getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(CatalogoDTO catalogo) {
        this.catalogo = catalogo;
    }
    

}
