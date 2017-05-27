package test.cdi;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.*;

@ApplicationScoped
public class PropertyProducer {

    @Produces
    @Property
    public String property(InjectionPoint ip) {
        Property annotation = ip.getAnnotated().getAnnotation(Property.class);
        return annotation.value() + "Property";
    }
}
