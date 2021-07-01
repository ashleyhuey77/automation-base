package com.warnermedia.data.mongo.config;

import com.warnermedia.data.loki.RandomService;

import java.util.Random;

public class Service {

    private static Random random = null;

    public Service() {
        this.random = new Random();
    }

    public static String numerify(String numberString) {
        StringBuilder sb = new StringBuilder();
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
