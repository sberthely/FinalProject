package edu.berkeley.ischool;

//Ask input from the user regarding interest rate and installments.
public class UserSavings
{
    private String name;
    private Double monthly_payment;
    private Double annual_interest_rate;
    private int num_installments;
    protected int paid_installments;
    final static int YEAR_MONTHLY_INSTALLMENTS = 12;

    public UserSavings(String name, Double monthly_payment, Double annual_interest_rate, int num_installments){
        this.name = name;
        this.monthly_payment = monthly_payment;
        this.annual_interest_rate = annual_interest_rate;
        this.num_installments = num_installments;
    }

    public boolean updatePaidInstallments(int paid_installments){
        if (this.num_installments < paid_installments) return false;
        this.paid_installments = paid_installments;
        return true;
    }

    public Double calculateMonthlyInterestRate() {
        return Math.round(annual_interest_rate / YEAR_MONTHLY_INSTALLMENTS * 100) / 100.00;
    }
}