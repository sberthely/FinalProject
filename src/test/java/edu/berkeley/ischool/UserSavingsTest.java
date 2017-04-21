package edu.berkeley.ischool;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UserSavingsTest {

    private UserSavings userSavings1, userSavings2, userSavings3, userSavings4, userSavings5;
    private String FILE_NAME;
    private UserMortgage userMortgage1, userMortgage2;

    @Before
    public void initObjects(){
        userSavings1 = new UserSavings("Selenne", 2000.0, 12.0, 10);
        userSavings2 = new UserSavings("Jose", 10000.0, 15.0, 20);
        userSavings3 = new UserSavings("Ramon", 1500.0, 9.5, 20);
        userSavings4 = new UserSavings("Julia", 1500.0, 0.0, 20);
        userSavings5 = new UserSavings("Liz", 1523.11, 7.4, 9);
        userMortgage1 = new UserMortgage("Rodrigo", 300000.00, 8.5, 180);
        userMortgage2 = new UserMortgage("Alexa", 1000000.00, 8.0, 360);
        FILE_NAME = System.getProperty("user.dir") + File.separator + "output.txt";

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
    public void fifteenMonthlyInterestRateShouldBeOnePointTwentyFive(){
        assertEquals(1.25, userSavings2.calculateMonthlyInterestRate());
    }

    @Test
    public void ninePointFiveMonthlyInterestRateShouldBePointSeventyNine(){
        assertEquals(0.79, userSavings3.calculateMonthlyInterestRate());
    }

    @Test
    public void zeroMonthlyInterestRateShouldBeZero(){
        assertEquals(0.0, userSavings4.calculateMonthlyInterestRate());
    }

    @Test
    public void sevenPointFourMonthlyInterestRateShouldBePointSixtyOne(){
        assertEquals(0.61, userSavings5.calculateMonthlyInterestRate());
    }

    //Test 3 - Calculate the earnings per installment --> calculateMonthlyEarning()
    @Test
    public void userSavings1MonthlyEarningsShouldBeTwenty(){
        assertEquals(20.0, userSavings1.calculateMonthlyEarning());
    }

    @Test
    public void allUserSavingsMonthlyEarningsShouldBeAsExpected(){
        assertEquals(125.0, userSavings2.calculateMonthlyEarning());
        assertEquals(11.85, userSavings3.calculateMonthlyEarning());
        assertEquals(0.0, userSavings4.calculateMonthlyEarning());
        assertEquals(9.29, userSavings5.calculateMonthlyEarning());
    }

    //Test 4 - Implemented the calculateTotalEarningsPerInstallment method and added a more complex savings decimal amounts.
    //From the tests I realised that the truncation was not working properly and the calculateMonthlyInterestRate() need to be fixed.
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

    //Test 5 - Calculate the CUMULATIVE Amount saved at the end of all the installments.
    //CUMULATIVE refers to the process of adding to the present installment the previous installment
    // plus the interest it earned in the last period.
    @Test
    public void allUserSavingsFinalSavingAmountShouldBeAsExpected(){
        assertEquals(21133.65, userSavings1.calculateCumulativeTotalEarnings());
        assertEquals(228450.08, userSavings2.calculateCumulativeTotalEarnings());
        assertEquals(32617.46, userSavings3.calculateCumulativeTotalEarnings());
        assertEquals(30000.0, userSavings4.calculateCumulativeTotalEarnings());
        assertEquals(14132.91, userSavings5.calculateCumulativeTotalEarnings());
    }

    //Test 6 - Created a generic method that calculates cumulative savings, and receives the number of
    // installments as a parameter, calculateCumulativeEarnings().
    // This method is used now in calculateSavingsToTheCurrentPaidPeriod() and calculateCumulativeTotalEarnings().
    @Test
    public void userSavings1ShouldReturnExpectedValueForFifthInstallment() {
        userSavings1.updatePaidInstallments(5);
        userSavings2.updatePaidInstallments(5);
        userSavings3.updatePaidInstallments(5);
        userSavings4.updatePaidInstallments(5);
        userSavings5.updatePaidInstallments(5);

        assertEquals(10304.02, userSavings1.calculateSavingsToTheCurrentPaidPeriod());
        assertEquals(51906.53, userSavings2.calculateSavingsToTheCurrentPaidPeriod());
        assertEquals(7679.62, userSavings3.calculateSavingsToTheCurrentPaidPeriod());
        assertEquals(7500.0, userSavings4.calculateSavingsToTheCurrentPaidPeriod());
        assertEquals(7756.03, userSavings5.calculateSavingsToTheCurrentPaidPeriod());
    }

    //Test 7 - Create userSavings with different information, calculate the savings and save them to a file.
    //Create the deleteTextFile(String aFileName) method to erase any existing file and create a new test.
    @Test
    public void readDataFromFileShouldWork() throws IOException {
        userSavings1.deleteTextFile(FILE_NAME);
        assertTrue(userSavings1.checkIfFileIsEmpty(FILE_NAME));
    }

    public void createAllUserSavingsToTheSameCVSFile() throws IOException {

        userSavings1.calculateCumulativeTotalEarnings();
        userSavings2.calculateCumulativeTotalEarnings();
        userSavings3.calculateCumulativeTotalEarnings();
        userSavings4.calculateCumulativeTotalEarnings();
        userSavings5.calculateCumulativeTotalEarnings();

        userSavings1.updatePaidInstallments(5);
        userSavings2.updatePaidInstallments(5);
        userSavings3.updatePaidInstallments(5);
        userSavings4.updatePaidInstallments(5);
        userSavings5.updatePaidInstallments(5);

        userSavings1.calculateSavingsToTheCurrentPaidPeriod();
        userSavings2.calculateSavingsToTheCurrentPaidPeriod();
        userSavings3.calculateSavingsToTheCurrentPaidPeriod();
        userSavings4.calculateSavingsToTheCurrentPaidPeriod();
        userSavings5.calculateSavingsToTheCurrentPaidPeriod();

        userSavings1.writeToTextFile(FILE_NAME);
        userSavings2.writeToTextFile(FILE_NAME);
        userSavings3.writeToTextFile(FILE_NAME);
        userSavings4.writeToTextFile(FILE_NAME);
        userSavings5.writeToTextFile(FILE_NAME);
    }

    @Test
    public void saveAllUserSavingsToTheSameCVSFile() throws IOException {
        createAllUserSavingsToTheSameCVSFile();
        List<String> lines = new ArrayList<>();

        lines.add("name,monthly_payment,annual_interest_rate,num_installments,totalCumulativeSavings");
        lines.add("Selenne,2000.0,12.0,10,21133.65,5,10304.02");
        lines.add("Jose,10000.0,15.0,20,228450.08,5,51906.53");
        lines.add("Ramon,1500.0,9.5,20,32617.46,5,7679.62");
        lines.add("Julia,1500.0,0.0,20,30000.0,5,7500.0");
        lines.add("Liz,1523.11,7.4,9,14132.91,5,7756.03");

        assertEquals(lines, userSavings5.readFromTextFile(FILE_NAME));
    }

    //Test 8 - Create InterestCalculator super class for UserSavings and UserMortgage and
    // create the calculateMonthlyPayment for Mortgages.
    @Test
    public void userSavingsDataShouldBeDisplayedFromFile() throws IOException {
        assertEquals(2954.21, userMortgage1.calculateMonthlyPayment());
        assertEquals(7337.64, userMortgage2.calculateMonthlyPayment());
    }


}