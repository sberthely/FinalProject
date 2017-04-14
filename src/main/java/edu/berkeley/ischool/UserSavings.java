package edu.berkeley.ischool;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Ask input from the user regarding interest rate and installments.
public class UserSavings
{
    final static int YEAR_MONTHLY_INSTALLMENTS = 12;
    private String name;
    private Double monthly_payment;
    private Double annual_interest_rate;
    private int num_installments;
    protected int paid_installments;
    private Double totalCumulativeSavings;
    private Double currentCumulativeSavings;
    final static Charset ENCODING = StandardCharsets.UTF_8;

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
        return truncateDecimal(annual_interest_rate / YEAR_MONTHLY_INSTALLMENTS, 2).doubleValue();
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

    private static BigDecimal truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
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


    public String readFromTextFile(String aFileName) throws IOException {
        String line;
        Path path = Paths.get(aFileName);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
            line = reader.readLine();
        }
        return line;
    }
}