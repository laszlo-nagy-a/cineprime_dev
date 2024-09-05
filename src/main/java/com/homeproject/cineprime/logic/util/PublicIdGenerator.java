package com.homeproject.cineprime.logic.util;

import java.security.SecureRandom;

public class PublicIdGenerator {

    public static final String RANDOMCHARS = "0123456789qwertzuiopasdfghjklyxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM";
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

        public static String generateId(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            returnValue.append(RANDOMCHARS.charAt(SECURE_RANDOM.nextInt(length)));
        }

        return returnValue.toString();
    }

}
