/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.CatalogoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.CatalogoPersistence;
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
public class CatalogoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CatalogoLogic catalogoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CatalogoEntity> data = new ArrayList<CatalogoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CatalogoEntity.class.getPackage())
                .addPackage(CatalogoLogic.class.getPackage())
                .addPackage(CatalogoPersistence.class.getPackage())
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
        em.createQuery("delete from CatalogoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CatalogoEntity entity = factory.manufacturePojo(CatalogoEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear un Catalogo
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createCatalogoTest() throws BusinessLogicException {
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
        CatalogoEntity result = catalogoLogic.crearCatalogo(newEntity);
        Assert.assertNotNull(result);
        CatalogoEntity entity = em.find(CatalogoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
        Assert.assertEquals(newEntity.getGaleria(), entity.getGaleria());
        Assert.assertEquals(newEntity.getObras(), entity.getObras());

    }

    /**
     * Prueba para consultar la lista de Catalogos
     *
     *
     */
    @Test
    public void getCatalogosTest() {
        List<CatalogoEntity> list = catalogoLogic.getCatalogos();
        Assert.assertEquals(data.size(), list.size());
        for (CatalogoEntity entity : list) {
            boolean found = false;
            for (CatalogoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Catalogo
     *
     *
     */
    @Test
    public void getCatalogoTest() {
        CatalogoEntity entity = data.get(0);
        CatalogoEntity resultEntity = catalogoLogic.getCatalogo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getCategoria(), resultEntity.getCategoria());
        Assert.assertEquals(entity.getGaleria(), resultEntity.getGaleria());
        Assert.assertEquals(entity.getObras(), resultEntity.getObras());

    }

    /**
     * Prueba para eliminar un Catalogo
     *
     *
     */
    @Test
    public void deleteCatalogoTest() {
        CatalogoEntity entity = data.get(0);
        catalogoLogic.deleteCatalogo(entity.getId());
        CatalogoEntity deleted = em.find(CatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Catalogo
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void updateCatalogoTest() throws BusinessLogicException {
        CatalogoEntity entity = data.get(0);
        CatalogoEntity pojoEntity = factory.manufacturePojo(CatalogoEntity.class);

        pojoEntity.setId(entity.getId());

        catalogoLogic.updateCatalogo(data.get(0).getId(), pojoEntity);

        CatalogoEntity resp = em.find(CatalogoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCategoria(), resp.getCategoria());
        Assert.assertEquals(pojoEntity.getGaleria(), resp.getGaleria());
        Assert.assertEquals(pojoEntity.getObras(), resp.getObras());

    }
}
