package com.happiness.happy.tire.util;

import java.util.UUID;

public class CommonUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID().length());
    }
}
