/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.CatalogoEntity;
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
public class CatalogoPersistenceTest {
        
    @Inject
    private CatalogoPersistence persistence;
    
    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<CatalogoEntity> data = new ArrayList<CatalogoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CatalogoEntity.class.getPackage())
                .addPackage(MarcoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     public CatalogoPersistenceTest() {
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
        em.createQuery("delete from CatalogoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CatalogoEntity entity = factory.manufacturePojo(CatalogoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Test of create method, of class CatalogoPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
        CatalogoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        CatalogoEntity entity = em.find(CatalogoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Test of find method, of class BlogPersistence.
     */
    @Test
    public void testFind() {
        CatalogoEntity entity = data.get(0);
        CatalogoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test of findAll method, of class CatalogoPersistence.
     */
    @Test
    public void testFindAll() {
        List<CatalogoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CatalogoEntity ent : list) {
            boolean found = false;
            for (CatalogoEntity entity : data) {
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
        CatalogoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        CatalogoEntity resp = em.find(CatalogoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Test of delete method, of class BlogPersistence.
     */
    @Test
    public void testDelete() {
        CatalogoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        CatalogoEntity deleted = em.find(CatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

