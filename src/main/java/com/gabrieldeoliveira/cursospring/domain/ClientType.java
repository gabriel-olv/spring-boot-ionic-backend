package com.gabrieldeoliveira.cursospring.domain;

import java.util.Arrays;

public enum ClientType {

    FISIC_PERSON(1, "Pessoa física"),
    JURIDIC_PERSON(2, "Pessoa jurídica");

    private int code;
    private String description;

    private ClientType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.asList(values()).stream()
                .filter(x -> x.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
