/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;



import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
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
 * @author jd.carrillor
 */
@RunWith(Arquillian.class)
public class ObraPersistenceTest
{
 @Inject
    private ObraPersistence persistence;

    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;
    

    @Inject
    UserTransaction utx;

    private List<ObraEntity> data = new ArrayList<ObraEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ObraEntity.class.getPackage())
                .addPackage(ObraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public ObraPersistenceTest() {
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
        em.createQuery("delete from ObraEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ObraEntity entity = factory.manufacturePojo(ObraEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

   
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);
        ObraEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        ObraEntity entity = em.find(ObraEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

   
    @Test
    public void testFind() {
        ObraEntity entity = data.get(0);
        ObraEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

   
    @Test
    public void testFindAll() {
        List<ObraEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ObraEntity ent : list) {
            boolean found = false;
            for (ObraEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    @Test
    public void testUpdate() {
        ObraEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ObraEntity resp = em.find(ObraEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

  
    @Test
    public void testDelete() {
        ObraEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ObraEntity deleted = em.find(ObraEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
}
