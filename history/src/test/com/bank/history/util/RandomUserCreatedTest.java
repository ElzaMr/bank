package com.bank.history.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomUserCreatedTest {
    @Test
    void checkRandomCreatedUser() {
        String firstName = RandomUserCreated.returnRandomUser();
        String secondName = RandomUserCreated.returnRandomUser();
        String thirdName = RandomUserCreated.returnRandomUser();
        assertNotEquals(firstName,secondName);
        assertNotEquals(secondName,thirdName);
        assertNotEquals(thirdName,firstName);
    }

}