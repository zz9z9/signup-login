package com.study.login.service;

import org.springframework.stereotype.Component;

@Component
public class Encryption {

    private String hashingAlgorithm = HashingAlgorithm.SHA256.getValue();

    public String getEncryptedPassword(String pw, String salt) {
        return null;
    }

    public String getSalt() {
        return null;
    }

}
