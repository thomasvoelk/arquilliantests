package test.cdi;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProducerTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return MinimalArchive.jar();
    }

    @Inject
    @Property("XXX")
    private String property;

    @Test
    public void producer() {
     assertEquals("XXXProperty", property);
    }
}
