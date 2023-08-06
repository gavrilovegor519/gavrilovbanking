package com.egor.gavrilovbanking.enums;

public enum RoleEnum {
    USER("ROLE_USER"),
    STAFF("ROLE_STAFF"),
    ADMIN("ROLE_ADMIN");

    private String role;

    private RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return role;
    }
}
