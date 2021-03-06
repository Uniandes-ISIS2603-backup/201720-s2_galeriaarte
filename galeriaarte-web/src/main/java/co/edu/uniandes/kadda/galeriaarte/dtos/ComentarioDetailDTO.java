package co.edu.uniandes.kadda.galeriaarte.dtos;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;

/**
 *
 * @author ks.estupinan
 */
public class ComentarioDetailDTO extends ComentarioDTO {

    private ClienteDTO cliente;
    private ObraDTO obra;

    public ComentarioDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ComentarioDetailDTO(ComentarioEntity entity) {
        super(entity);
        if (entity.getClienteComentario() != null) {
            this.cliente = new ClienteDTO(entity.getClienteComentario());
        } else {
            entity.setClienteComentario(null);
        }
        if (entity.getObra() != null) {
            this.obra = new ObraDTO(entity.getObra());
        } else {
            entity.setObra(null);
        }
    }

    public ObraDTO getObra() {
        return obra;
    }

    public void setObra(ObraDTO obra) {
        this.obra = obra;
    }

    @Override
    public ComentarioEntity toEntity() {
        ComentarioEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setClienteComentario(this.getCliente().toEntity());
        }
        if (this.getObra() != null) {
            entity.setObra(this.getObra().toEntity());
        }
        return entity;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }
}
