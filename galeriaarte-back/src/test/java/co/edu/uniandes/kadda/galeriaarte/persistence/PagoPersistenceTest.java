/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.persistence;


import co.edu.uniandes.kadda.galeriaarte.entities.CompraEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.PagoEntity;
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
public class PagoPersistenceTest {
     
     @Inject
    private PagoPersistence persistence;

    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PagoEntity> data = new ArrayList<PagoEntity>();
    
    private List<CompraEntity> data2 = new ArrayList<CompraEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    
    public PagoPersistenceTest() {
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
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            CompraEntity entityC = factory.manufacturePojo(CompraEntity.class);
            
            entity.setCompra(entityC);
            entityC.setPago(entity);
            em.persist(entity);
            em.persist(entityC);
            data.add(entity);
            data2.add(entityC);
            
        }
    }
    

    /*i
     * Test of find method, of class PagoPersistence.
     */
    @Test
    public void testFind() {
        CompraEntity entity = data2.get(0);
        PagoEntity entity2 = entity.getPago();
        PagoEntity newEntity = persistence.find(entity.getId(), entity2.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity2.getId(), newEntity.getId());
    }


    /**
     * Test of create method, of class PagoPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        PagoEntity entity = em.find(PagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of delete method, of class PagoPersistence.
     */
    @Test
    public void testDelete() {
        PagoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    

    
   
    
}
