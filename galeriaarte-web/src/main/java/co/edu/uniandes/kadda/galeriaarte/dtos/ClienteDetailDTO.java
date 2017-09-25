/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ks.estupinan
 */
public class ClienteDetailDTO extends ClienteDTO {

    //private List<ObraDTO> obras;
    private List<ComentarioDTO> comentarios;

    public ClienteDetailDTO()
    {
    }
    public ClienteDetailDTO(ClienteEntity entity) {
        super();
        if (entity != null && entity.getComentarios() != null) {
            comentarios = new ArrayList<>();
            for (ComentarioEntity entityComentarios : entity.getComentarios()) {
                comentarios.add(new ComentarioDTO(entityComentarios));
            }

        }
    }

//    public List<ObraDTO> getObras() {
//        return obras;
//    }
//
//    public void setObras(List<ObraDTO> obras) {
//        this.obras = obras;
//    }
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
        if (comentarios != null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO comentarioDto : comentarios) {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            entity.setComentarios(comentariosEntity);
        }

        return entity;
    }
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }


}