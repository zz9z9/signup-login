package com.study.login.service;

import lombok.Getter;

@Getter
public enum HashAlgorithm {
    SHA256("SHA-256"),
    SHA512("SHA-512");

    private final String value;

    HashAlgorithm(String value) {
        this.value = value;
    }
}
