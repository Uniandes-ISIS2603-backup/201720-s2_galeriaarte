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
package co.edu.uniandes.kadda.galeriaarte.entities;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603
 */
@Entity
public class GaleriaEntity extends BaseEntity implements Serializable 
{
//    @Id
//   private Long id;
   //private String nombre;
   private String direccion;
   private int telefono;
   
//   @PodamExclude
//   @OneToMany(mappedBy = "artistasGaleria")
//   private List<ArtistaEntity> artistas;
//   
//   @PodamExclude
//   @OneToMany(mappedBy = "catalogoGaleria")
//   private List<CatalogoEntity> catalogos;
//   
//   @PodamExclude
//   @OneToMany(mappedBy = "clienteGaleria")
//   private List<ClienteEntity> clientes;
   
//   @Override
//   public Long getId()
//   {
//       return id;
//   }
//
//    /**
//     *
//     * @param pId
//     */
//    @Override
//   public void setId(Long pId)
//   {
//       this.id = pId;
//   }
//   public String getNombre()
//   {
//       return nombre;
//   }
//   public void setNombre(String pNombre)
//   {
//       this.nombre = pNombre;
//   }
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
//   public List<ArtistaEntity> getArtistas()
//   {
//       return artistas;
//   }
//   public void setArtistas(List<ArtistaEntity> pArtistas)
//   {
//       this.artistas = pArtistas;
//   }
//   public List<CatalogoEntity> getCatalogos()
//   {
//       return catalogos;
//   }
//   public void setCatalogos(List<CatalogoEntity> pCatalogos)
//   {
//       this.catalogos = pCatalogos;
//   }
//   public List<ClienteEntity> getClientes()
//   {
//       return clientes;
//   }
//   public void setClientes(List<ClienteEntity> pClientes)
//   {
//       this.clientes = pClientes;
//   }
   
}
