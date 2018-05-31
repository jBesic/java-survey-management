package com.jbesic.helpers;

public class JBHelperFunctions {

    private static int last_generated_id = 0;

    public static String createUniqueID() {
        return String.valueOf(last_generated_id++);
    }
}
