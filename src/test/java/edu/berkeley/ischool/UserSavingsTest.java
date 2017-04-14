package edu.berkeley.ischool;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class UserSavingsTest {

    UserSavings userSavings1, userSavings2, userSavings3, userSavings4, userSavings5;

    @Before
    public void initObjects(){
        userSavings1 = new UserSavings("Selenne", 2000.0, 12.0, 10);
        userSavings2 = new UserSavings("Jose", 10000.0, 15.0, 20);
        userSavings3 = new UserSavings("Ramon", 1500.0, 9.5, 20);
        userSavings4 = new UserSavings("Ramon", 1500.0, 0.0, 20);
        userSavings5 = new UserSavings("Ramon", 1523.11, 7.4, 9);
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

    //Test 4 - Implemented the calculateTotalEarningsPerInstallment method and added a more complex savings decimal amounts.
    //From the tests I realised that the truncation was not working properly and the calculateMonthlyInterestRate() need to be fixed.

    //Test 5 Delivery
    @Test
    public void userSavings1TotalEarningsPerInstallmentShouldBeTwoHundred(){
        assertEquals(200.0, userSavings1.calculateTotalEarningsPerInstallment());
    }

    @Test
    public void allUserSavingsTotalEarningsPerInstallmentShouldBeAsExpected(){
        assertEquals(2500.0, userSavings2.calculateTotalEarningsPerInstallment());
        assertEquals(237.0, userSavings3.calculateTotalEarningsPerInstallment());
        assertEquals(0.0, userSavings4.calculateTotalEarningsPerInstallment());
        assertEquals(83.60, userSavings5.calculateTotalEarningsPerInstallment());
    }

}