/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.BlogEntity;
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
public class BlogPersistenceTest {
    
    @Inject
    private BlogPersistence persistence;
    
    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<BlogEntity> data = new ArrayList<BlogEntity>();
    
    private List<ArtistaEntity> data2 = new ArrayList<ArtistaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     public BlogPersistenceTest() {
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
        em.createQuery("delete from BlogEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        List<BlogEntity> b = new ArrayList<BlogEntity>();
        for (int i = 0; i < 3; i++) {
            BlogEntity entity = factory.manufacturePojo(BlogEntity.class);
            ArtistaEntity entityC = factory.manufacturePojo(ArtistaEntity.class);
            b.add(entity);
            entity.setArtista(entityC);
            entityC.setBlogs(b);
            em.persist(entity);
            em.persist(entityC);
            data.add(entity);
            data2.add(entityC);
        }
    }
    
    /**
     * Test of create method, of class BlogPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        BlogEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        BlogEntity entity = em.find(BlogEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Test of find method, of class BlogPersistence.
     */
    @Test
    public void testFind() {
        ArtistaEntity entity = data2.get(0);
        List<BlogEntity> entity2 = entity.getBlogs();
        int i = 0;
        for(i = 0;i< entity2.size();i++)
        {
        BlogEntity newEntity = persistence.find(entity.getId(), entity2.get(0).getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity2.get(0).getId(), newEntity.getId());
        }
    }
    
    /**
     * Test of findAll method, of class BlogPersistence.
     */
    @Test
    public void testFindAll() {
        List<BlogEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (BlogEntity ent : list) {
            boolean found = false;
            for (BlogEntity entity : data) {
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
        BlogEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        BlogEntity resp = em.find(BlogEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Test of delete method, of class BlogPersistence.
     */
    @Test
    public void testDelete() {
        BlogEntity entity = data.get(0);
        persistence.delete(entity.getId());
        BlogEntity deleted = em.find(BlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
