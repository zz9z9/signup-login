package com.study.login.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class Encryption {

    private final int SALT_SIZE = 16;

    public abstract String getEncryptedPassword(String pw, String salt) throws NoSuchAlgorithmException;

    public String getSalt() {
        SecureRandom rnd = new SecureRandom();
        byte[] byteSalt = new byte[SALT_SIZE];
        rnd.nextBytes(byteSalt);

        return byteToString(byteSalt);
    }

    public String byteToString(byte[] byteSalt) {
        StringBuilder sb = new StringBuilder();
        for(byte b : byteSalt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
