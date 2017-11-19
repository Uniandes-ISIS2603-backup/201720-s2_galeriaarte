/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import java.util.ArrayList;

/**
 *
 * @author Daniel Perilla
 */
public class CatalogoDetailDTO extends CatalogoDTO {

    // relación  cero o muchos reviews 
    private ArrayList<ObraDTO> obras = new ArrayList<>();

    public CatalogoDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CatalogoDetailDTO(CatalogoEntity entity) {
        super(entity);
        if (entity != null) {
            obras = new ArrayList<>();
            for (ObraEntity entityObra : entity.getObras()) {
                obras.add(new ObraDTO(entityObra));
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public CatalogoEntity toEntity() {
        CatalogoEntity catalogo = super.toEntity();
        if (obras != null) {
            ArrayList<ObraEntity> obrasEntity = new ArrayList<>();
            for (ObraDTO dtoObra : obras) {
                obrasEntity.add(dtoObra.toEntity());
            }
            catalogo.setObras(obrasEntity);
        }
        return catalogo;
    }

    /**
     * @return obras
     */
    public ArrayList<ObraDTO> getObras() {
        return obras;
    }

    /**
     * @param obras to set
     */
    public void setObras(ArrayList<ObraDTO> pObras) {
        this.obras = pObras;
    }

}
