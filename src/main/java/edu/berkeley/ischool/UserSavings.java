package edu.berkeley.ischool;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//Ask input from the user regarding interest rate and installments.
public class UserSavings
{
    private String name;
    private Double monthly_payment;
    private Double interest_rate;
    private int num_installments;
    protected int paid_installments;

    public UserSavings(String name, Double monthly_payment, Double interest_rate, int num_installments){
        this.name = name;
        this.monthly_payment = monthly_payment;
        this.interest_rate = interest_rate;
        this.num_installments = num_installments;
    }


    public boolean updatePaidInstallments(int paid_installments){
        if (this.num_installments < paid_installments) return false;
        this.paid_installments = paid_installments;
        return true;
    }
}