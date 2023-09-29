package com.bank.history.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для рандомного создания юзеров
 */
public class RandomUserCreated {
    private static final List<String> userList;

    static {
        userList = new ArrayList<>();
        for(int i=1;i<=100;i++){
            userList.add("User"+i);
        }
    }

    /**
     * @return String которая содержит случайное имя из списка userList
     */
    public static String returnRandomUser() {
        Random random = new Random();
        int min = 0;
        int max = userList.size() - 1;
        int randomInt = random.nextInt(max - min) + min;
        return userList.get(randomInt);
    }
}
