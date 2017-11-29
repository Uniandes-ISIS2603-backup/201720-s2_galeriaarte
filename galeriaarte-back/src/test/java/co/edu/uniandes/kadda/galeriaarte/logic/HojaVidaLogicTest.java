/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.HojaVidaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.HojaVidaPersistence;
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
public class HojaVidaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HojaVidaLogic hojaVidaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<HojaVidaEntity> data = new ArrayList<HojaVidaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HojaVidaEntity.class.getPackage())
                .addPackage(HojaVidaLogic.class.getPackage())
                .addPackage(HojaVidaPersistence.class.getPackage())
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
        em.createQuery("delete from HojaVidaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            HojaVidaEntity entity = factory.manufacturePojo(HojaVidaEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear una Hoja de Vida
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createHojaVidaTest() throws BusinessLogicException {
        HojaVidaEntity newEntity = factory.manufacturePojo(HojaVidaEntity.class);
        HojaVidaEntity result = hojaVidaLogic.createHojaVida(newEntity);
        Assert.assertNotNull(result);
        HojaVidaEntity entity = em.find(HojaVidaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTrayectoria(), entity.getTrayectoria());
        Assert.assertEquals(newEntity.getAlmaMater(), entity.getAlmaMater());
        Assert.assertEquals(newEntity.getNacionalidad(), entity.getNacionalidad());
    }

    /**
     * Prueba para consultar la lista de Artistas
     *
     *
     */
    @Test
    public void getHojasVidaTest() {
        List<HojaVidaEntity> list = hojaVidaLogic.getHojasVida();
        Assert.assertEquals(data.size(), list.size());
        for (HojaVidaEntity entity : list) {
            boolean found = false;
            for (HojaVidaEntity storedEntity : data) {
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
    public void getHojaVidaTest() {
        HojaVidaEntity entity = data.get(0);
        HojaVidaEntity resultEntity = hojaVidaLogic.getHojaVida(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTrayectoria(), resultEntity.getTrayectoria());
        Assert.assertEquals(entity.getAlmaMater(), resultEntity.getAlmaMater());
        Assert.assertEquals(entity.getNacionalidad(), resultEntity.getNacionalidad());

    }

    /**
     * Prueba para eliminar un Artista
     *
     *
     */
    @Test
    public void deleteHojaVidaTest() {
        HojaVidaEntity entity = data.get(0);
        hojaVidaLogic.delete(entity.getId());
        HojaVidaEntity deleted = em.find(HojaVidaEntity.class, entity.getId());
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
    public void updateHojavidaTest() throws BusinessLogicException {
        HojaVidaEntity entity = data.get(0);
        HojaVidaEntity pojoEntity = factory.manufacturePojo(HojaVidaEntity.class);

        pojoEntity.setId(entity.getId());

        hojaVidaLogic.update(pojoEntity);

        HojaVidaEntity resp = em.find(HojaVidaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTrayectoria(), resp.getTrayectoria());
        Assert.assertEquals(pojoEntity.getAlmaMater(), resp.getAlmaMater());
        Assert.assertEquals(pojoEntity.getNacionalidad(), resp.getNacionalidad());

    }
}
