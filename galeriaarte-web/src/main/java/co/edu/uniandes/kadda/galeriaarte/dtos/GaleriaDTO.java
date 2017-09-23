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
import javax.persistence.OneToMany;

/**
 * GaleriaDTO Objeto de transferencia de datos de Galerias. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author ISIS2603
 */
public class GaleriaDTO 
{
    
   private Long id;
   private String nombre;
   private String direccion;
   private int telefono;
   
   private ArrayList<ArtistaDTO> artistas;
   
   
   private ArrayList<CatalogoDTO> catalogos;
   
   
   private ArrayList<ClienteDTO> clientes;
   
    /**
     * Constructor por defecto
     */
    public GaleriaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param galeria: Es la entidad que se va a convertir a DTO
     */
    public GaleriaDTO(GaleriaEntity galeria) {
        this.id = galeria.getId();
        this.nombre = galeria.getNombre();
        this.direccion = galeria.getDireccion();
        this.telefono = galeria.getTelefono();
        
  
    }

     /** Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public GaleriaEntity toEntity() {
        GaleriaEntity entity = new GaleriaEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        return entity;
    }
    
   
   public Long getId()
   {
       return id;
   }

    /**
     * @param pId
     */
   
   public void setId(Long pId)
   {
       this.id = pId;
   }
   public String getNombre()
   {
       return nombre;
   }
   public void setNombre(String pNombre)
   {
       this.nombre = pNombre;
   }
   public String getDireccion()
   {
       return direccion;
   }
   public void setDireccion(String pDireccion)
   {
       this.direccion = pDireccion;
   }
   public int getTelefono()
   {
       return telefono;
   }
   public void setTelefono(int pTelefono)
   {
       this.telefono = pTelefono;
   }
   
}
