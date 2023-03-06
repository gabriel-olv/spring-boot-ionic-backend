package com.gabrieldeoliveira.cursospring.domain.enums;

import java.util.Arrays;

public enum PaymentStatus {
    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(3, "Cancelado");

    private Integer code;
    private String description;

    private PaymentStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus fromCode(Integer code) {
        return Arrays.asList(values()).stream()
                .filter(x -> x.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
