package test.cdi;

import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.*;

public class MinmalArchive {
    public static JavaArchive jar() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("test.cdi")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
