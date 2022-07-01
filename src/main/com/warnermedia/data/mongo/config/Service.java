package com.warnermedia.data.mongo.config;

import java.util.Random;

public class Service {

    public Service() {

    }

    public static String numerify(String numberString) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(random.nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }
        return sb.toString();
    }
}
