package com.bank.history.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RandomUserCreatedTest {
    private static String result;

    @BeforeAll
    public static void setUp() {
        result = RandomUserCreated.returnRandomUser();
    }

    @Test
    void checkRandomCreatedUser() {
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            names.add(RandomUserCreated.returnRandomUser());
        }
        assertEquals(100, names.size());
    }


    @Test
    void returnValueShouldNotBeNull() {
        assertNotNull(result);
    }
}