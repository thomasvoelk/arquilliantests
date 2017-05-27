package test.cdi;

import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;

public class MinimalArchive {
    public static JavaArchive jar() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("test.cdi")
                .addAsManifestResource("test-beans.xml", "beans.xml");
    }
}
