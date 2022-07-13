package com.example.demo.model.enums;

public enum Role {
    ROLE_USER,
    ROLE_MANAGER;

    @Override
    public String toString() {
        return this.name().replace("ROLE_", "");
    }
}

