/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.MarcoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.MarcoPersistence;
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
 * @author d.perilla11
 */
@RunWith(Arquillian.class)
public class MarcoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MarcoLogic marcoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MarcoEntity> data = new ArrayList<MarcoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MarcoEntity.class.getPackage())
                .addPackage(MarcoLogic.class.getPackage())
                .addPackage(MarcoPersistence.class.getPackage())
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
        em.createQuery("delete from MarcoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            MarcoEntity entity = factory.manufacturePojo(MarcoEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear un Artista
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createMarcoTest() throws BusinessLogicException {
        MarcoEntity newEntity = factory.manufacturePojo(MarcoEntity.class);
        MarcoEntity result = marcoLogic.createMarco(newEntity);
        Assert.assertNotNull(result);
        MarcoEntity entity = em.find(MarcoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMaterial(), entity.getMaterial());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        
    }

    /**
     * Prueba para consultar la lista de Artistas
     *
     *
     */
    @Test
    public void getMarcosTest() {
        List<MarcoEntity> list = marcoLogic.getMarcos();
        Assert.assertEquals(data.size(), list.size());
        for (MarcoEntity entity : list) {
            boolean found = false;
            for (MarcoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Artista
     *
     *
     */
    @Test
    public void getMarcoTest() {
        MarcoEntity entity = data.get(0);
        MarcoEntity resultEntity = marcoLogic.getMarco(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getMaterial(), resultEntity.getMaterial());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());

    }

    /**
     * Prueba para eliminar un Artista
     *
     *
     */
    @Test
    public void deleteMarcoTest() {
        MarcoEntity entity = data.get(0);
        marcoLogic.deleteMarco(entity.getId());
        MarcoEntity deleted = em.find(MarcoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Artista
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void updateMarcoTest() throws BusinessLogicException {
        MarcoEntity entity = data.get(0);
        MarcoEntity pojoEntity = factory.manufacturePojo(MarcoEntity.class);

        pojoEntity.setId(entity.getId());

        marcoLogic.updateMarco(data.get(0).getId(), pojoEntity);

        MarcoEntity resp = em.find(MarcoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getMaterial(), resp.getMaterial());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
        

    }
}
