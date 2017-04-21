package edu.berkeley.ischool;

//Calculates user Mortgage according to loan Amount, interest rate and number of installments
public class UserMortgage extends InterestCalculation {

    final static int YEAR_MONTHLY_INSTALLMENTS = 12;
    protected Double loan_amount;
    private Double loan_still_to_pay;
    protected double ppal_paid_from_loan;
    protected double cumulative_paid_with_interest;
    protected double cumulative_interest_paid;
    protected double cumulative_principal_paid;


    public UserMortgage(String name, Double loan_amount, Double annual_interest_rate, int num_installments){
        super(annual_interest_rate, name, num_installments);
        this.loan_amount = loan_amount;
    }

    public Double calculateMonthlyPayment() {
        double interest_rate = annual_interest_rate / 100.0;
        double monthly_rate = interest_rate / YEAR_MONTHLY_INSTALLMENTS;
        return truncateDecimal((loan_amount * monthly_rate) / (1 - Math.pow(1 + monthly_rate, - (num_installments + 2))), 2).doubleValue();
    }

    public boolean updatePaidInstallments(int paid_installments){
        return super.updatePaidInstallments(paid_installments);
    }

    public double calculateStillToPayToCurrentPeriod() {

        double interest_proportion;
        double capital_proportion;

        loan_still_to_pay = loan_amount;

        for (int iterator = 1; iterator <= paid_installments; iterator++){
            interest_proportion = loan_still_to_pay * calculateMonthlyInterestRate() / 100;
            capital_proportion = calculateMonthlyPayment() - interest_proportion;

            if (loan_still_to_pay < capital_proportion) capital_proportion = loan_still_to_pay;

            loan_still_to_pay -= capital_proportion;
            cumulative_paid_with_interest += interest_proportion + capital_proportion;
            cumulative_interest_paid += interest_proportion;
            cumulative_principal_paid += capital_proportion;
        }

        ppal_paid_from_loan = loan_amount - loan_still_to_pay;

        return truncateDecimal(loan_still_to_pay, 2).doubleValue();

    }
}
