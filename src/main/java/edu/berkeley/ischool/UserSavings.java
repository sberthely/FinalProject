package edu.berkeley.ischool;

import java.io.IOException;
import java.text.DecimalFormat;

//Calculates user savings at the end of a period according to monthly payment, interest rate and number of installments.
public class UserSavings extends InterestCalculation {

//    private Double totalCumulativeSavings;
    private Double currentCumulativeSavings;

    public UserSavings(String name, Double monthly_payment, Double annual_interest_rate, int num_installments){
        super(annual_interest_rate, name, num_installments);
        this.monthly_payment = monthly_payment;
    }

    public UserSavings(UserMortgage userMortgage){
        super(userMortgage.annual_interest_rate, userMortgage.name, userMortgage.num_installments);
        this.monthly_payment = userMortgage.monthly_payment;
        super.loan_amount = userMortgage.loan_amount;
        updatePaidInstallments(userMortgage.paid_installments);
    }

    public boolean updatePaidInstallments(int paid_installments){
       return super.updatePaidInstallments(paid_installments);
    }

    public Double calculateMonthlyEarning() {
        return truncateDecimal(calculateMonthlyInterestRate() * monthly_payment / 100, 2).doubleValue();
    }

    public Double calculateTotalEarningsPerInstallment() {
        return truncateDecimal(calculateMonthlyEarning() * num_installments, 2).doubleValue();
    }

    public double calculateCumulativeTotalEarnings() {
        totalCumulativeSavings = calculateCumulativeEarnings(num_installments);
        return totalCumulativeSavings;
    }

    public double calculateSavingsToTheCurrentPaidPeriod() {
        currentCumulativeSavings = calculateCumulativeEarnings(paid_installments);
        return currentCumulativeSavings;
    }

    public double calculateCumulativeEarnings(int installments) {
        int inst;
        Double cumulativeEarnings = 0.00;
        for (inst = 0; inst < installments; inst++){
            cumulativeEarnings += monthly_payment;
            cumulativeEarnings = truncateDecimal(cumulativeEarnings + (calculateMonthlyInterestRate() * cumulativeEarnings / 100), 2).doubleValue();
        }
        return cumulativeEarnings;
    }

    public void retrieveFormattedUserData() throws IOException {

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        super.retrieveFormattedUserData();
//        System.out.println("Monthly Payment: " + formatter.format(monthly_payment));

        System.out.println("----SAVINGS----");
        System.out.println("Current Cumulative Savings: " + formatter.format(currentCumulativeSavings));
        System.out.println("Total Cumulative Savings: " + formatter.format(totalCumulativeSavings) + "\n");

//        String currentSaved = String.format("$%10.2f", Double.parseDouble(cells[6]));
//        System.out.println("Current Saved Amount: " + currentSaved);
//        String finalAmount = String.format("$%10.2f", Double.parseDouble(cells[4]));
//        System.out.println("Final Amount to be Saved: " + finalAmount);
//
//        int installmentsToGo = Integer.parseInt(cells[3]) -  Integer.parseInt(cells[5]);
//        String installmentPayment = String.format("$%10.2f", Double.parseDouble(cells[1]));
//        System.out.println(String.valueOf(installmentsToGo) + " remaining installments of " + installmentPayment + "\n");
    }


    @Override
    public String toString() {
        return name + ',' + monthly_payment + ',' + annual_interest_rate + ',' + num_installments + ',' + totalCumulativeSavings + ',' +
                paid_installments + ',' + currentCumulativeSavings;
    }

}