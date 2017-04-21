package edu.berkeley.ischool;

/**
 * Created by SelBerthely on 4/20/17.
 */
public class UserMortgage extends InterestCalculation {

    final static int YEAR_MONTHLY_INSTALLMENTS = 12;
    protected Double loan_amount;
//    protected int num_installments;


    public UserMortgage(String name, Double loan_amount, Double annual_interest_rate, int num_installments){
        super(annual_interest_rate, name, num_installments);
        this.loan_amount = loan_amount;
    }

    public Double calculateMonthlyPayment() {
        double interest_rate = annual_interest_rate / 100.0;
        double monthly_rate = interest_rate / YEAR_MONTHLY_INSTALLMENTS;
        return truncateDecimal((loan_amount * monthly_rate) / (1 - Math.pow(1 + monthly_rate, - num_installments)), 2).doubleValue();
    }
}
