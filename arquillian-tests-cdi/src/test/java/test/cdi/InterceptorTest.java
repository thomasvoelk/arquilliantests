package test.cdi;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class InterceptorTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return MinimalArchive.jar();
    }

    @Inject
    Greeter greeter;

    @Test
    public void interceptorAddsXxx() {
        String test = greeter.interceptorAddsXxxTo("Test");
        assertEquals("TestXxx", test);
    }

}
