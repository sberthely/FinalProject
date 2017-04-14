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


    public String readFromTextFile(String aFileName) throws IOException {
        String line;
        Path path = Paths.get(aFileName);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
            line = reader.readLine();
        }
        return line;
    }

}