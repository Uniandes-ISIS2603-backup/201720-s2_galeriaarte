/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

}
