package edu.berkeley.ischool;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Superclass that holds the common properties between userSavings and UserMortgage calculations.
public class InterestCalculation {
    final static int YEAR_MONTHLY_INSTALLMENTS = 12;
    protected String name;
    protected Double annual_interest_rate;
    protected int num_installments;
    protected int paid_installments;
    protected Double monthly_payment;
    protected Double loan_amount;
    protected Double totalCumulativeSavings;

    public InterestCalculation(Double annual_interest_rate, String name, int num_installments) {
        this.annual_interest_rate = annual_interest_rate;
        this.name = name;
        this.num_installments = num_installments;
    }

    protected static BigDecimal truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }

    public boolean updatePaidInstallments(int paid_installments){
        if (this.num_installments < paid_installments) return false;
        this.paid_installments = paid_installments;
        return true;
    }

    public Double calculateMonthlyInterestRate() {
        return truncateDecimal(annual_interest_rate / YEAR_MONTHLY_INSTALLMENTS, 2).doubleValue();
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
    }List<String> readFromTextFile(String aFileName) throws IOException {
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
             BufferedWriter writer = new BufferedWriter(fw)
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

    public void retrieveFormattedUserData() throws IOException {

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        System.out.println("----GENERAL USER INFO----");
        System.out.println("Name: " + name);
        System.out.println("Annual Interest Rate: " + annual_interest_rate);
        System.out.println("Number of Installments: " + num_installments + " months");
        System.out.println("Paid Installments: " + paid_installments + " months");
        System.out.println("Monthly Payment: " + formatter.format(monthly_payment) + '\n');
        System.out.println("Unsaved Money: " + formatter.format(unsavedMoney()) + '\n');
//        String currentSaved = String.format("$%10.2f", Double.parseDouble(cells[6]));
//        System.out.println("Current Saved Amount: " + currentSaved);
//        String finalAmount = String.format("$%10.2f", Double.parseDouble(cells[4]));
//        System.out.println("Final Amount to be Saved: " + finalAmount);
//
//        int installmentsToGo = Integer.parseInt(cells[3]) -  Integer.parseInt(cells[5]);
//        String installmentPayment = String.format("$%10.2f", Double.parseDouble(cells[1]));
//        System.out.println(String.valueOf(installmentsToGo) + " remaining installments of " + installmentPayment + "\n");

    }

    public double unsavedMoney() {
        return totalCumulativeSavings - loan_amount;
    }
}
