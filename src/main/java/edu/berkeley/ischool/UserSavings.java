package edu.berkeley.ischool;

//Calculates user savings at the end of a period according to monthly payment, interest rate and number of installments.
public class UserSavings extends InterestCalculation {

    protected Double monthly_payment;
    private Double totalCumulativeSavings;
    private Double currentCumulativeSavings;

    public UserSavings(String name, Double monthly_payment, Double annual_interest_rate, int num_installments){
        super(annual_interest_rate, name, num_installments);
        this.monthly_payment = monthly_payment;
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

    @Override
    public String toString() {
        return name + ',' + monthly_payment + ',' + annual_interest_rate + ',' + num_installments + ',' + totalCumulativeSavings + ',' +
                paid_installments + ',' + currentCumulativeSavings;
    }

}