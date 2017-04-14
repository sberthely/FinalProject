package edu.berkeley.ischool;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class UserSavingsTest {

    private String FILE_NAME;

    @Before
    public void initObjects(){
        FILE_NAME = "/Users/SelBerthely/IdeaProjects/FinalProject/src/output.txt";
    }

    @Test
    public void readDataFromFileShouldWork() throws IOException {
        UserSavings userSavings = new UserSavings();

        assertEquals(null, userSavings.readFromTextFile(FILE_NAME));

    }

}