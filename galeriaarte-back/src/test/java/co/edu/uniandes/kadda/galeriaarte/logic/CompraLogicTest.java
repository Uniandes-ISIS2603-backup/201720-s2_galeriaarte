/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.logic;

import co.edu.uniandes.kadda.galeriaarte.ejb.CompraLogic;
import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.CompraPersistence;
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
public class CompraLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CompraLogic compraLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CompraEntity> data = new ArrayList<CompraEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraLogic.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
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
        em.createQuery("delete from CompraEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CompraEntity entity = factory.manufacturePojo(CompraEntity.class);
            em.persist(entity);
            data.add(entity);

        }

    }
    
    /**
     * Prueba para crear una Compra
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void createCompraTest() throws BusinessLogicException {
        CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);
        CompraEntity result = compraLogic.createCompra(newEntity);
        Assert.assertNotNull(result);
        CompraEntity entity = em.find(CompraEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(newEntity.getObras(), entity.getObras());
        Assert.assertEquals(newEntity.getPago(), entity.getPago());
      
    }
    
    /**
     * Prueba para consultar la lista de Compras
     *
     *
     */
    @Test
    public void getComprasTest() {
        List<CompraEntity> list = compraLogic.getCompras();
        Assert.assertEquals(data.size(), list.size());
        for (CompraEntity entity : list) {
            boolean found = false;
            for (CompraEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Compra
     *
     *
     */
    @Test
    public void getCompraTest() {
        CompraEntity entity = data.get(0);
        CompraEntity resultEntity = compraLogic.getCompra(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getName(), entity.getName());
        Assert.assertEquals(resultEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(resultEntity.getObras(), entity.getObras());
        Assert.assertEquals(resultEntity.getPago(), entity.getPago());

    }
    
    /**
     * Prueba para eliminar una Compra
     *
     *
     */
    @Test
    public void deleteCompraTest() {
        CompraEntity entity = data.get(0);
        compraLogic.deleteCompra(entity.getId());
        CompraEntity deleted = em.find(CompraEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una Compra
     *
     *
     * @throws
     * co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException
     */
    @Test
    public void updateCompraTest() throws BusinessLogicException {
        CompraEntity entity = data.get(0);
        CompraEntity pojoEntity = factory.manufacturePojo(CompraEntity.class);

        pojoEntity.setId(entity.getId());

        compraLogic.updateCompra(pojoEntity);

        CompraEntity resp = em.find(CompraEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCliente(), resp.getCliente());
        Assert.assertEquals(pojoEntity.getObras(), resp.getObras());
        Assert.assertEquals(pojoEntity.getPago(), resp.getPago());

    }
}
