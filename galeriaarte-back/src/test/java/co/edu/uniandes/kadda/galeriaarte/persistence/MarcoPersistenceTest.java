/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.MarcoEntity;
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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author af.leon
 */
@RunWith(Arquillian.class)
public class MarcoPersistenceTest {
    
    @Inject
    private MarcoPersistence persistence;
    
    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<MarcoEntity> data = new ArrayList<MarcoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MarcoEntity.class.getPackage())
                .addPackage(MarcoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     public MarcoPersistenceTest() {
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
        em.createQuery("delete from MarcoEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MarcoEntity entity = factory.manufacturePojo(MarcoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     
     /**
     * Test of create method, of class MarcoPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        MarcoEntity newEntity = factory.manufacturePojo(MarcoEntity.class);
        MarcoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        MarcoEntity entity = em.find(MarcoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Test of find method, of class MarcoPersistence.
     */
    @Test
    public void testFind() {
        MarcoEntity entity = data.get(0);
        MarcoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test of findAll method, of class MarcoPersistence.
     */
    @Test
    public void testFindAll() {
        List<MarcoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MarcoEntity ent : list) {
            boolean found = false;
            for (MarcoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of update method, of class MarcoPersistence.
     */
    @Test
    public void testUpdate() {
        MarcoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MarcoEntity newEntity = factory.manufacturePojo(MarcoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        MarcoEntity resp = em.find(MarcoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Test of delete method, of class MarcoPersistence.
     */
    @Test
    public void testDelete() {
        MarcoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        MarcoEntity deleted = em.find(MarcoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
