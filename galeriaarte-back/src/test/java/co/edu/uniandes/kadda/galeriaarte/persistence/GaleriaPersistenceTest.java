/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.GaleriaEntity;
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
 * @author Daniel Perilla
 */
@RunWith(Arquillian.class)
public class GaleriaPersistenceTest 
{
    @Inject
    private GaleriaPersistence persistence;
    
    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<GaleriaEntity> data = new ArrayList<GaleriaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GaleriaEntity.class.getPackage())
                .addPackage(MarcoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     public GaleriaPersistenceTest() {
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
        em.createQuery("delete from GaleriaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GaleriaEntity entity = factory.manufacturePojo(GaleriaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Test of create method, of class GaleriaPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        GaleriaEntity newEntity = factory.manufacturePojo(GaleriaEntity.class);
        GaleriaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        GaleriaEntity entity = em.find(GaleriaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Test of find method, of class GaleriaPersistence.
     */
    @Test
    public void testFind() {
        GaleriaEntity entity = data.get(0);
        GaleriaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test of findAll method, of class GaleriaPersistence.
     */
    @Test
    public void testFindAll() {
        List<GaleriaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GaleriaEntity ent : list) {
            boolean found = false;
            for (GaleriaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of update method, of class BlogPersistence.
     */
    @Test
    public void testUpdate() {
        GaleriaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GaleriaEntity newEntity = factory.manufacturePojo(GaleriaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        GaleriaEntity resp = em.find(GaleriaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Test of delete method, of class BlogPersistence.
     */
    @Test
    public void testDelete() {
        GaleriaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        GaleriaEntity deleted = em.find(GaleriaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
    
