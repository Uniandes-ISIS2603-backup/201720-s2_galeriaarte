/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
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
public class HojaVidaPersistenceTest
{
 @Inject
    private HojaVidaPersistence persistence;

    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<HojaVidaEntity> data = new ArrayList<HojaVidaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HojaVidaEntity.class.getPackage())
                .addPackage(HojaVidaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public HojaVidaPersistenceTest() {
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
        em.createQuery("delete from HojaVidaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HojaVidaEntity entity = factory.manufacturePojo(HojaVidaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

   
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        HojaVidaEntity newEntity = factory.manufacturePojo(HojaVidaEntity.class);
        HojaVidaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        HojaVidaEntity entity = em.find(HojaVidaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    
    @Test
    public void testFind() {
        HojaVidaEntity entity = data.get(0);
        HojaVidaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    
    @Test
    public void testFindAll() {
        List<HojaVidaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HojaVidaEntity ent : list) {
            boolean found = false;
            for (HojaVidaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

  
    @Test
    public void testUpdate() {
        HojaVidaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HojaVidaEntity newEntity = factory.manufacturePojo(HojaVidaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        HojaVidaEntity resp = em.find(HojaVidaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    
    @Test
    public void testDelete() {
        HojaVidaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HojaVidaEntity deleted = em.find(HojaVidaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
}
