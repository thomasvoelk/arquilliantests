/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.ejb.nointerface;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ejb.*;
import javax.inject.*;

import static org.junit.Assert.*;

/**
 * NoInterfaceEJBTestCase
 * 
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
@RunWith(Arquillian.class)
public class NoInterfaceEJBTestCase {
    @Deployment
    public static JavaArchive createDeployment() {
        // explicit archive name required until ARQ-77 is resolved
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClass(NoInterfaceEJB.class);
    }

    @Inject
    NoInterfaceEJB ejb;

    // NOTE fails on OpenEJB
    @Test
    public void shouldBeAbleToResolveAndInvokeNoInterfaceEJB() throws Exception {
        assertNotNull("Verify that the ejb was injected", ejb);

        assertEquals("Verify that the ejb returns correct value", "pong", ejb.ping());
    }
}
