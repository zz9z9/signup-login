package com.study.login.service;

import lombok.Getter;

@Getter
public enum HashingAlgorithm {
    SHA256("SHA-256"),
    SHA512("SHA-512");

    private final String value;

    HashingAlgorithm(String value) {
        this.value = value;
    }
}
