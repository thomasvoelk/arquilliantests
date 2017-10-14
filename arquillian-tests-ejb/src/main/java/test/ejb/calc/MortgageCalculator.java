package test.ejb.calc;

import javax.ejb.*;
import java.math.*;

@Local
public interface MortgageCalculator {
    BigDecimal calculateMonthlyPayment(double principal, double interestRate, int termYears);

    BigDecimal calculateMonthlyPayment(double principal, int termYears);

    double getCurrentInterestRate();
}
