/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.ObraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ObraPersistence;
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
public class ObraLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ObraLogic obraLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ObraEntity> data = new ArrayList<ObraEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ObraEntity.class.getPackage())
                .addPackage(ObraLogic.class.getPackage())
                .addPackage(ObraPersistence.class.getPackage())
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
        em.createQuery("delete from ObraEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ObraEntity entity = factory.manufacturePojo(ObraEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear una Obra
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createObraTest() throws BusinessLogicException {
        ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);
        ObraEntity result = obraLogic.createObra(newEntity);
        Assert.assertNotNull(result);
        ObraEntity entity = em.find(ObraEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCantidad(), entity.getCantidad());
    }

    /**
     * Prueba para consultar la lista de Obras
     *
     *
     */
    @Test
    public void getObrasTest() {
        List<ObraEntity> list = obraLogic.getObras();
        Assert.assertEquals(data.size(), list.size());
        for (ObraEntity entity : list) {
            boolean found = false;
            for (ObraEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una obra
     *
     *
     */
    @Test
    public void getObraTest() {
        ObraEntity entity = data.get(0);
        ObraEntity resultEntity = obraLogic.getObra(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getImagen(), resultEntity.getImagen());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        Assert.assertEquals(entity.getCantidad(), resultEntity.getCantidad());
        
    }

    /**
     * Prueba para eliminar un Artista
     *
     *
     */
    @Test
    public void deleteObraTest() {
        ObraEntity entity = data.get(0);
        obraLogic.delete(entity.getId());
        ObraEntity deleted = em.find(ObraEntity.class, entity.getId());
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
        ObraEntity entity = data.get(0);
        ObraEntity pojoEntity = factory.manufacturePojo(ObraEntity.class);

        pojoEntity.setId(entity.getId());

        obraLogic.update(pojoEntity);

        ObraEntity resp = em.find(ObraEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getImagen(), resp.getImagen());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
        Assert.assertEquals(pojoEntity.getCantidad(), resp.getCantidad());
        
    }
}
