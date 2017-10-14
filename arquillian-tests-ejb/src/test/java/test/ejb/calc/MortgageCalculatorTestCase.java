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
public class MortgageCalculatorTestCase {
    @Deployment
    public static JavaArchive createDeployment() {
        // explicit archive name required until ARQ-77 is resolved
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MortgageCalculator.class, MortgageCalculatorBean.class);
    }

    @Inject
    MortgageCalculator calculator;

    @Test
    public void shouldCalculateMonthlyPaymentAccurately() {
        // calculator = new MortgageCalculatorBean();

        double principal = 750000;
        double rate = 7.5;
        int term = 30;
        BigDecimal expected = new BigDecimal(Double.toString(5244.11));

        BigDecimal actual = calculator.calculateMonthlyPayment(principal, rate, term);
        Assert.assertEquals("A banking error has been detected!", expected, actual);

        principal = 2500000;
        rate = 5.5;
        term = 30;
        expected = new BigDecimal(Double.toString(14194.72));

        actual = calculator.calculateMonthlyPayment(principal, rate, term);
        Assert.assertEquals("A banking error has been detected!", expected, actual);
    }
}
