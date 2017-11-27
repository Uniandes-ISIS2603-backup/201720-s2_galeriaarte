package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;

/**
 *
 * @author ks.estupinan
 */
public class ClienteDTO {

    private Long id;
    private String name;
    private String tipoTarjeta;
    private int numTarjeta;

    public ClienteDTO() {
    }

    public ClienteDTO(ClienteEntity entidad) {
        if (entidad != null) {
            this.id = entidad.getId();
            this.name = entidad.getName();
            this.tipoTarjeta = entidad.getTipoTarjeta();
            this.numTarjeta = entidad.getNumTarjeta();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setNumtarjeta(this.numTarjeta);
        entity.setTipoTarjeta(this.tipoTarjeta);
        return entity;
    }
}
