package edu.berkeley.ischool;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class UserSavingsTest {

    UserSavings userSavings1, userSavings2, userSavings3, userSavings4;

    @Before
    public void initObjects(){
        userSavings1 = new UserSavings("Selenne", 2000.0, 12.0, 10);
        userSavings2 = new UserSavings("Jose", 10000.0, 15.0, 20);
        userSavings3 = new UserSavings("Ramon", 1500.0, 9.5, 20);
        userSavings4 = new UserSavings("Ramon", 1500.0, 0.0, 20);
    }

    //Test 1 - Create a userSavings class and update paid_installments.
    @Test
    public void updatePaidInstallmentsShouldWork(){
        userSavings1.updatePaidInstallments(5);
        assertEquals(5, userSavings1.paid_installments);
    }

    @Test
    public void updateMorePaidInstallmentsThanPossibleShouldNotWork(){
        assertFalse(userSavings1.updatePaidInstallments(15));
    }

    //Test 2 - Calculate the monthly_interest_rate based on each user annual_interest_rate --> calculateMonthlyInterestRate().
    @Test
    public void twelveMonthlyInterestRateShouldBe1(){
        assertEquals(1.00, userSavings1.calculateMonthlyInterestRate());
    }

    @Test
    public void fifteenMonthlyInterestRateShouldBe1Dot25(){
        assertEquals(1.25, userSavings2.calculateMonthlyInterestRate());
    }

    @Test
    public void nineDotFiveMonthlyInterestRateShouldBeDot79(){
        assertEquals(0.79, userSavings3.calculateMonthlyInterestRate());
    }

    @Test
    public void zeroMonthlyInterestRateShouldBeZero(){
        assertEquals(0.0, userSavings4.calculateMonthlyInterestRate());
    }

    //Test 3 - Calculate the earnings per installment --> calculateMonthlyEarning
    @Test
    public void userSavings1MonthlyEarningsShouldBeTwenty(){
        assertEquals(20.0, userSavings1.calculateMonthlyEarning());
    }

    @Test
    public void allUserSavingsMonthlyEarningsShouldBeAsExpected(){
        assertEquals(125.0, userSavings2.calculateMonthlyEarning());
        assertEquals(11.85, userSavings3.calculateMonthlyEarning());
        assertEquals(0.0, userSavings4.calculateMonthlyEarning());
    }
}