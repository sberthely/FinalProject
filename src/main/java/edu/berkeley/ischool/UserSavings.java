package edu.berkeley.ischool;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
//    final static Charset ENCODING = StandardCharsets.UTF_8;

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

    void deleteTextFile(String aFileName) throws IOException {
        try {
            File f = new File(aFileName);

            //Delete file if exists
            f.delete();
            //Create a new file
            f.createNewFile();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    List<String> readFromTextFile(String aFileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(aFileName);
             BufferedReader reader = new BufferedReader(fr)){
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }
        return lines;
    }

    public boolean writeToTextFile(String aFileName) throws IOException {

        try (FileWriter fw = new FileWriter(aFileName, true);
             BufferedWriter writer = new BufferedWriter(fw);
        ) {
            if (checkIfFileIsEmpty(aFileName)) {
                writer.write("name,monthly_payment,annual_interest_rate,num_installments,totalCumulativeSavings");
                writer.newLine();
            }

            writer.append(toString());
            writer.newLine();
            writer.close();
        }
        return true;
    }

    public Boolean checkIfFileIsEmpty(String aFileName) throws IOException {
        FileReader fr = new FileReader(aFileName);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        reader.close();

        if (line == null)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return name + ',' + monthly_payment + ',' + annual_interest_rate + ',' + num_installments + ',' + totalCumulativeSavings + ',' +
                paid_installments + ',' + currentCumulativeSavings;
    }
}