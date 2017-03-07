package com.ads.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static PasswordUtil instance = null;

    private PasswordUtil() {
    }

    public static PasswordUtil getInstance() {
        if (instance == null) {
            instance = new PasswordUtil();
        }
        return instance;
    }

    public String textPasswordToHash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

        messageDigest.reset();
        messageDigest.update(password.getBytes());

        byte[] output = messageDigest.digest();

        StringBuilder stringBuffer = new StringBuilder();
        for (byte tmpByte : output) {
            stringBuffer.append(Integer.toString((tmpByte & 0xff) + 0x100, 16).substring(1));
        }

        return stringBuffer.toString();
    }

    public void foo() {
        System.out.println("doing nothing");
    }
}
