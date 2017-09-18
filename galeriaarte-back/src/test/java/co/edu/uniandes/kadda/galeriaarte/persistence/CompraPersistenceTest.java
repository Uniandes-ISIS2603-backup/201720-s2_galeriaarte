/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ma.abril
 */
@RunWith(Arquillian.class)
public class CompraPersistenceTest {
    
    @Inject
    private CompraPersistence persistence;
    
    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;
    
     @Inject
    UserTransaction utx;

    private List<CompraEntity> data = new ArrayList<CompraEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public CompraPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
    
    @After
    public void tearDown() {
    }

        private void clearData() {
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CompraEntity entity = factory.manufacturePojo(CompraEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Test of find method, of class CompraPersistence.
     */
    @Test
    public void testFind() throws Exception {
        CompraEntity entity = data.get(0);
        CompraEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class CompraPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<CompraEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CompraEntity ent : list) {
            boolean found = false;
            for (CompraEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class CompraPersistence.
     */
    @Test
    public void testCreate() throws Exception {
     PodamFactory factory = new PodamFactoryImpl();
        CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);
        CompraEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        CompraEntity entity = em.find(CompraEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of update method, of class CompraPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        CompraEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        CompraEntity resp = em.find(CompraEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of delete method, of class CompraPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        CompraEntity entity = data.get(0);
        persistence.delete(entity.getId());
        CompraEntity deleted = em.find(CompraEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
