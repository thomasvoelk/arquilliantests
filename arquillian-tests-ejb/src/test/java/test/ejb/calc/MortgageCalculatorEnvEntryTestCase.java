package test.ejb.calc;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.Test;
import org.junit.runner.*;

import javax.ejb.*;
import javax.inject.*;
import java.math.*;

@RunWith(Arquillian.class)
public class MortgageCalculatorEnvEntryTestCase {
    @Deployment
    public static Archive<?> createDeployment() {
        // we have to create a war because ejb-jar.xml must be put in WEB-INF
        // explicit archive name required until ARQ-77 is resolved
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MortgageCalculator.class, MortgageCalculatorBean.class)
                .addAsWebInfResource("interest-rate-ejb-jar.xml", "ejb-jar.xml");
        return war;
    }

    @Inject
    MortgageCalculator calculator;

    @Test
    public void shouldCalculateMonthlyPaymentAccuratelyWithBuiltInRate() {
        Assert.assertEquals("Interest rate should be set by ejb-jar.xml", 5.5, calculator.getCurrentInterestRate());

        double principal = 750000;
        int term = 30;
        BigDecimal expected = new BigDecimal(Double.toString(4258.42));

        BigDecimal actual = calculator.calculateMonthlyPayment(principal, term);
        Assert.assertEquals("A banking error has been detected!", expected, actual);
    }
}
