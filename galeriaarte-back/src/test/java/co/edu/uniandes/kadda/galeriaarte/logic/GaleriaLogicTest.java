/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.GaleriaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.GaleriaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author af.leon
 */
@RunWith(Arquillian.class)
public class GaleriaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private GaleriaLogic galeriaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<GaleriaEntity> data = new ArrayList<GaleriaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GaleriaEntity.class.getPackage())
                .addPackage(GaleriaLogic.class.getPackage())
                .addPackage(GaleriaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from GaleriaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            GaleriaEntity entity = factory.manufacturePojo(GaleriaEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }
    
    /**
     * Prueba para crear una Galeria
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createGaleriaTest() throws BusinessLogicException {
        GaleriaEntity newEntity = factory.manufacturePojo(GaleriaEntity.class);
        GaleriaEntity result = galeriaLogic.createGaleria(newEntity);
        Assert.assertNotNull(result);
        GaleriaEntity entity = em.find(GaleriaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getArtistas(), entity.getArtistas());
        Assert.assertEquals(newEntity.getCatalogos(), entity.getCatalogos());
        Assert.assertEquals(newEntity.getClientes(), entity.getClientes());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        
    }
    
     /**
     * Prueba para consultar la lista de Galerias
     *
     *
     */
    @Test
    public void getGaleriasTest() {
        List<GaleriaEntity> list = galeriaLogic.getGalerias();
        Assert.assertEquals(data.size(), list.size());
        for (GaleriaEntity entity : list) {
            boolean found = false;
            for (GaleriaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar una Galeria
     *
     *
     */
    @Test
    public void deleteGaleriaTest() {
        GaleriaEntity entity = data.get(0);
        galeriaLogic.deleteGaleria(entity.getId());
        GaleriaEntity deleted = em.find(GaleriaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una Galeria
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void updateGaleriaTest() throws BusinessLogicException {
        GaleriaEntity entity = data.get(0);
        GaleriaEntity pojoEntity = factory.manufacturePojo(GaleriaEntity.class);

        pojoEntity.setId(entity.getId());

        galeriaLogic.updateGaleria(pojoEntity);

        GaleriaEntity resp = em.find(GaleriaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getArtistas(), resp.getArtistas());
        Assert.assertEquals(pojoEntity.getCatalogos(), resp.getCatalogos());
        Assert.assertEquals(pojoEntity.getClientes(), resp.getClientes());
        Assert.assertEquals(pojoEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getTelefono(), resp.getTelefono());

    }
}
