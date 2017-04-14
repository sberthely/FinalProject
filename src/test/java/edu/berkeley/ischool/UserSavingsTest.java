package edu.berkeley.ischool;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class UserSavingsTest {

    UserSavings userSavings;

    @Before
    public void initObjects(){
        userSavings = new UserSavings("Selenne", 2000.0, 5.0, 10);

    }

    @Test
    public void updatePaidInstallmentsShouldWork(){
        userSavings.updatePaidInstallments(5);
        assertEquals(5, userSavings.paid_installments);
    }

    @Test
    public void updateMorePaidInstallmentsThanPossibleShouldNotWork(){
        assertFalse(userSavings.updatePaidInstallments(15));
    }
}