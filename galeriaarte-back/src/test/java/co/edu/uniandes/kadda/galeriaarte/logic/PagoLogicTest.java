/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.ArtistaLogic;
import co.edu.uniandes.kadda.galeriaarte.ejb.PagoLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import co.edu.uniandes.kadda.galeriaarte.persistence.PagoPersistence;
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
public class PagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PagoEntity> data = new ArrayList<PagoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
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
        em.createQuery("delete from PagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
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
    public void createPagoTest() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = pagoLogic.createPago(newEntity.getCompra().getId(), newEntity);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTotal(), entity.getTotal());
        Assert.assertEquals(newEntity.getImpuesto(), entity.getImpuesto());
        
    }

    /**
     * Prueba para consultar la lista de Artistas
     *
     *
     */
    @Test
    public void getPagosTest() {
        List<PagoEntity> list = pagoLogic.getPagos();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity entity : list) {
            boolean found = false;
            for (PagoEntity storedEntity : data) {
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
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(entity.getCompra().getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTotal(), resultEntity.getTotal());
        Assert.assertEquals(entity.getImpuesto(), resultEntity.getImpuesto());
        
       
    }

    /**
     * Prueba para eliminar un Artista
     *
     *
     */
    @Test
    public void deletePagoTest() {
        PagoEntity entity = data.get(0);
        pagoLogic.deletePago(entity.getCompra().getId(), entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
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
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);

        pojoEntity.setId(entity.getId());

        pagoLogic.updatePago(pojoEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTotal(), resp.getTotal());
        Assert.assertEquals(pojoEntity.getImpuesto(), resp.getImpuesto());
       

    }
}
