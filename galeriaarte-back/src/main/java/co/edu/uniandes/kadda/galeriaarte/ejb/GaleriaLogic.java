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
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ClienteEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.CatalogoPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.ClientePersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.GaleriaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class GaleriaLogic {

   private static final Logger LOGGER = Logger.getLogger(GaleriaLogic.class.getName());

    @Inject
    private GaleriaPersistence persistence; // Variable para acceder a la persistencia de la aplicaciÃ³n. Es una inyecciÃ³n de dependencias.

    @Inject
    private CatalogoPersistence persistenceCat; //Variable para acceder a la persistencia del catÃƒÂ¡logo;
    
    @Inject
    private ClientePersistence persistenceCli;
    
    @Inject 
    private ArtistaPersistence persistenceArt;

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public GaleriaEntity createGaleria(GaleriaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creaciÃ³n de Galeria");
        // Invoca la persistencia para crear la Galeria
        persistence.create(entity);
        LOGGER.info("Termina proceso de creaciÃ³n de Galeria");
        return entity;
    }

    public GaleriaEntity getGaleria()
    {
        LOGGER.info("Inicia procesod de consultar Galeria");
        GaleriaEntity galeria = null;
        List<GaleriaEntity> galerias = persistence.findAll();
        for (int i = 0; i < galerias.size(); i++)
        {
            if(galerias.get(i) != null)
            {
                galeria = galerias.get(i);
            }
        }
        return galeria;
    }
    /**
     * 
     * Obtener todas las Galerias existentes en la base de datos.
     *
     * @return una lista de Galerias.
     */
    public List<GaleriaEntity> getGalerias() {
        LOGGER.info("Inicia proceso de consultar todas las Galerias");
        // Note que, por medio de la inyecciÃ³n de dependencias se llama al mÃ©todo "findAll()" que se encuentra en la persistencia.
        List<GaleriaEntity> galerias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Galerias");
        return galerias;
    }

    public GaleriaEntity  updateGaleria(GaleriaEntity entity)
    {      
           return persistence.update(entity);
    }
    

    
    
     public void deleteGaleria(Long id)
    {
       persistence.delete(id);
    }
     
     public List<ClienteEntity> getClientes() 
    {
        LOGGER.info("Inicia proceso de consultar todos los clientes");
        List<ClienteEntity> clientes = persistenceCli.findAll();
        LOGGER.info("Termina proceso de consultar todos los clientes");
        return clientes;
    }
    
    
    
    public List<ArtistaEntity> getArtistas()
    {
        LOGGER.info("Inicia proceso de consultar todos los artistas");
        List<ArtistaEntity> artistas = persistenceArt.findAll();
        LOGGER.info("Termina proceso de consultar todos los artistas");
        return artistas;
    }
    
    public List<CatalogoEntity> getCatalogos()
    {
        LOGGER.info("Inicia proceso de consultar todos los catÃ¡logos");
        List<CatalogoEntity> catalogos = persistenceCat.findAll();
        LOGGER.info("Termina proceso de consultar todos los artistas");
        return catalogos;
    }
    
    //No solo agrega una galeria al cliente sino que además agrega un cliente a una galeria.
    public void addGaleriaACliente(long idCliente)
    {
        LOGGER.info("Inicia proceso de agragar la galería al cliente");
        ClienteEntity cliente = persistenceCli.find(idCliente);
        GaleriaEntity galeria = getGaleria();
        List<ClienteEntity> clientes = galeria.getClientes();
        clientes.add(cliente);
        cliente.setClienteGaleria(galeria);
    }

}
