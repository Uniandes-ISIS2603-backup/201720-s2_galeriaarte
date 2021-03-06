package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ks.estupinan
 */
public class ClienteDetailDTO extends ClienteDTO {

    private List<ObraDTO> obras;
    private List<ComentarioDTO> comentarios;
    private List<CompraDTO> compras;

    public ClienteDetailDTO() {
        super();
    }

    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getComentarios() != null) {
                comentarios = new ArrayList<>();
                for (ComentarioEntity entityComentarios : entity.getComentarios()) {
                    comentarios.add(new ComentarioDTO(entityComentarios));
                }
            }
            if (entity.getObra() != null) {
                obras = new ArrayList<>();
                for (ObraEntity entityObras : entity.getObra()) {
                    obras.add(new ObraDTO(entityObras));
                }
            }
            if (entity.getCompra() != null) {
                compras = new ArrayList<>();
                for (CompraEntity entityCompras : entity.getCompra()) {
                    compras.add(new CompraDTO(entityCompras));
                }
            }
        }
    }

    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
        if (comentarios != null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO comentarioDto : comentarios) {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            entity.setComentarios(comentariosEntity);
        }
        if (obras != null) {
            List<ObraEntity> obraEntity = new ArrayList<>();
            for (ObraDTO obraDto : obras) {
                obraEntity.add(obraDto.toEntity());
            }
            entity.setObra(obraEntity);
        }
        return entity;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<ObraDTO> getObras() {
        return obras;
    }

    public void setObras(List<ObraDTO> obras) {
        this.obras = obras;
    }

    public List<CompraDTO> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraDTO> compras) {
        this.compras = compras;
    }
}
