/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISIS2603
 */
public class GaleriaDetailDTO extends GaleriaDTO {

    // relación  cero o muchos reviews 
    private List<CatalogoDTO> catalogos = new ArrayList<>();

    // relación  cero o muchos author
    private List<ClienteDTO> clientes = new ArrayList<>();

    // relación ceo o muchos con artistas
    private List<ArtistaDTO> artistas = new ArrayList<>();

    public GaleriaDetailDTO() {
        super();
    }

    /**
     * Constructor para pasar de un Entity a un DTO
     *
     * @param entity
     */
    public GaleriaDetailDTO(GaleriaEntity entity) {
        super(entity);
        if (entity != null) {
            catalogos = new ArrayList<>();
            for (CatalogoEntity entityCatalogo : entity.getCatalogos()) {
                catalogos.add(new CatalogoDTO(entityCatalogo));
            }

            clientes = new ArrayList<>();
            for (ClienteEntity entityCliente : entity.getClientes()) {
                clientes.add(new ClienteDTO(entityCliente));
            }

            for (ArtistaEntity entityArtista : entity.getArtistas()) {
                artistas.add(new ArtistaDTO(entityArtista));
            }
        }
    }

    @Override
    public GaleriaEntity toEntity() {
        GaleriaEntity galeria = super.toEntity();
        if (catalogos != null) {
            ArrayList<CatalogoEntity> catalogosEntity = new ArrayList<>();
            for (CatalogoDTO dtoCatalogo : catalogos) {
                catalogosEntity.add(dtoCatalogo.toEntity());
            }
            galeria.setCatalogos(catalogosEntity);
        }

        if (clientes != null) {
            ArrayList<ClienteEntity> clientesEntity = new ArrayList<>();

            for (ClienteDTO dtoCliente : clientes) {
                clientesEntity.add(dtoCliente.toEntity());
            }
            galeria.setClientes(clientesEntity);
        }

        if (artistas != null) {
            ArrayList<ArtistaEntity> artistasEntity = new ArrayList<>();
            for (ArtistaDTO dtoArtista : artistas) {
                artistasEntity.add(dtoArtista.toEntity());
            }
            galeria.setArtistas(artistasEntity);
        }
        return galeria;
    }

    /**
     * @return los catalogos
     */
    public List<CatalogoDTO> getCatalogos() {
        return catalogos;
    }

    /**
     * @param pCatalogos los catalogos to set
     */
    public void setCatalogos(List<CatalogoDTO> pCatalogos) {
        this.catalogos = pCatalogos;
    }

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> pClientes) {
        this.clientes = pClientes;
    }

    public List<ArtistaDTO> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<ArtistaDTO> pArtistas) {
        this.artistas = pArtistas;
    }
}
