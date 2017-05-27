package test.cdi;

import javax.enterprise.util.*;
import javax.inject.*;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
public @interface Property {
    @Nonbinding
    String value() default "";
}
