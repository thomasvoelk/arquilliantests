package test.jpa;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;
import javax.interceptor.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.*;
import java.util.*;

@RunWith(Arquillian.class)
public class PersistenceTest {
    @Deployment
    public static Archive<?> createDeployment() {
        // You can use war packaging...
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Game.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    private static final String[] GAME_TITLES = {
            "Super Mario Brothers",
            "Mario Kart",
            "F-Zero"
    };

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Inject
    GameRepository repository;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from Game").executeUpdate();
        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        for (String title : GAME_TITLES) {
            Game game = new Game(title);
            em.persist(game);
        }
        utx.commit();
        // reset the persistence context (cache)
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    @Test
    public void shouldFindAllGames() throws Exception {
        List<Game> games = repository.findAll();
        System.out.println("Found " + games.size() + " games (using JPQL):");
        assertContainsAllGames(games);
    }

    private static void assertContainsAllGames(Collection<Game> retrievedGames) {
        Assert.assertEquals(GAME_TITLES.length, retrievedGames.size());
        final Set<String> retrievedGameTitles = new HashSet<String>();
        for (Game game : retrievedGames) {
            System.out.println("* " + game);
            retrievedGameTitles.add(game.getTitle());
        }
        Assert.assertTrue(retrievedGameTitles.containsAll(Arrays.asList(GAME_TITLES)));
    }
}
