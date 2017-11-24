package co.edu.uniandes.kadda.galeriaarte.persistence;

import co.edu.uniandes.kadda.galeriaarte.entities.ComentarioEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ks.estupinan
 */
@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {

    @Inject
    private ComentarioPersistence persistence;

    @PersistenceContext(unitName = "galeriadeartePU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public ComentarioPersistenceTest() {
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class ComentarioPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of find method, of class ComentarioPersistence.
     */
    @Test
    public void testFind() {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class ComentarioPersistence.
     */
    @Test
    public void testFindAll() {
        List<ComentarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity ent : list) {
            boolean found = false;
            for (ComentarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of update method, of class ComentarioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        ComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of delete method, of class ComentarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        ComentarioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
