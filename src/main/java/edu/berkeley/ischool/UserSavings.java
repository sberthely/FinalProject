

package edu.berkeley.ischool;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Ask input from the user regarding interest rate and installments.
public class UserSavings
{
    final static Charset ENCODING = StandardCharsets.UTF_8;
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

    public String readFromTextFile(String aFileName) throws IOException {
        String line;
        Path path = Paths.get(aFileName);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
            line = reader.readLine();
        }
        return line;
    }

    public boolean updatePaidInstallments(int paid_installments){
        if (this.num_installments < paid_installments) return false;
        this.paid_installments = paid_installments;
        return true;
    }
}