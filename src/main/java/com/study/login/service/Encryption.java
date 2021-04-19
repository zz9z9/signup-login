package com.study.login.service;

import java.security.SecureRandom;

public abstract class Encryption {

    protected HashAlgorithm hashAlgorithm;
    private final int SALT_SIZE = 16;

    public Encryption() {
    }

    public Encryption(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public abstract String getEncryptedPassword(String pw, String salt);

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
