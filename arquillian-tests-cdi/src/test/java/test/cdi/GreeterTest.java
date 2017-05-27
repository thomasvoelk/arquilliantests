package test.cdi;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GreeterTest {

    @Deployment
    public static JavaArchive createDeployment() {

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage("test.cdi")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

    @Inject
    Greeter greeter;


    @Test
    public void shouldGreet() {
        assertEquals("Hello, world", greeter.createGreeting("world"));
    }
}