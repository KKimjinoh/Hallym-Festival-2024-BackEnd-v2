package com.kkimjinoh.global.util;

import java.util.UUID;

public class CodeGenerator {

    public static String generateRandomCode(int length) {
        return UUID.randomUUID().toString().replace("-", "")
                .substring(0, length).toUpperCase();
    }

}
