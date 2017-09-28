/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
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
}
