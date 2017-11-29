/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
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
public class ArtistaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ArtistaLogic artistaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ArtistaEntity> data = new ArrayList<ArtistaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArtistaEntity.class.getPackage())
                .addPackage(ArtistaLogic.class.getPackage())
                .addPackage(ArtistaPersistence.class.getPackage())
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
        em.createQuery("delete from ArtistaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ArtistaEntity entity = factory.manufacturePojo(ArtistaEntity.class);
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
    public void createArtistaTest() throws BusinessLogicException {
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = artistaLogic.createArtista(newEntity);
        Assert.assertNotNull(result);
        ArtistaEntity entity = em.find(ArtistaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getBlogs(), entity.getBlogs());
        Assert.assertEquals(newEntity.getGaleria(), entity.getGaleria());
        Assert.assertEquals(newEntity.getHojaVida(), entity.getHojaVida());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getObras(), entity.getObras());
    }

    /**
     * Prueba para consultar la lista de Artistas
     *
     *
     */
    @Test
    public void getArtistasTest() {
        List<ArtistaEntity> list = artistaLogic.getArtistas();
        Assert.assertEquals(data.size(), list.size());
        for (ArtistaEntity entity : list) {
            boolean found = false;
            for (ArtistaEntity storedEntity : data) {
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
    public void getArtistaTest() {
        ArtistaEntity entity = data.get(0);
        ArtistaEntity resultEntity = artistaLogic.getArtista(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getBlogs(), resultEntity.getBlogs());
        Assert.assertEquals(entity.getGaleria(), resultEntity.getGaleria());
        Assert.assertEquals(entity.getHojaVida(), resultEntity.getHojaVida());
        Assert.assertEquals(entity.getImagen(), resultEntity.getImagen());
        Assert.assertEquals(entity.getObras(), resultEntity.getObras());

    }

    /**
     * Prueba para eliminar un Artista
     *
     *
     */
    @Test
    public void deleteArtistaTest() {
        ArtistaEntity entity = data.get(0);
        artistaLogic.deleteArtista(entity.getId());
        ArtistaEntity deleted = em.find(ArtistaEntity.class, entity.getId());
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
    public void updateArtistaTest() throws BusinessLogicException {
        ArtistaEntity entity = data.get(0);
        ArtistaEntity pojoEntity = factory.manufacturePojo(ArtistaEntity.class);

        pojoEntity.setId(entity.getId());

        artistaLogic.updateArtista(data.get(0).getId(), pojoEntity);

        ArtistaEntity resp = em.find(ArtistaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getBlogs(), resp.getBlogs());
        Assert.assertEquals(pojoEntity.getGaleria(), resp.getGaleria());
        Assert.assertEquals(pojoEntity.getHojaVida(), resp.getHojaVida());
        Assert.assertEquals(pojoEntity.getImagen(), resp.getImagen());
        Assert.assertEquals(pojoEntity.getObras(), resp.getObras());

    }
}
