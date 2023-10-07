package com.bank.history.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Класс для рандомного создания юзеров
 */


public class RandomUserCreated {
    private static final List<String> userList;

    static {
        userList = new LinkedList<>();
        for(int i=1;i<=100;i++){
            userList.add("User"+i);
        }
    }

    public static String returnRandomUser() {
        if (userList.isEmpty()) {
            return null; // or throw an exception
        }
        Random random = new Random();
        int randomIndex = random.nextInt(userList.size());
        return userList.remove(randomIndex);
    }
}