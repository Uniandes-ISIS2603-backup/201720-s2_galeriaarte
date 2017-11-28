package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ks.estupinan
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private ClienteEntity clienteComentario;

    @PodamExclude
    @ManyToOne
    private ObraEntity obra;

    /**
     * @return ClienteEntity
     */
    public ClienteEntity getClienteComentario() {
        return clienteComentario;
    }

    /**
     * @param clienteComentario
     */
    public void setClienteComentario(ClienteEntity clienteComentario) {
        this.clienteComentario = clienteComentario;
    }

    /**
     * @return ObraEntity
     */
    public ObraEntity getObra() {
        return obra;
    }

    /**
     * @param obra
     */
    public void setObra(ObraEntity obra) {
        this.obra = obra;
    }
    
    @Override
     public boolean equals(Object obj) {
         if(obj instanceof ComentarioEntity)
             return super.equals(obj);
         return false;
     }
}
