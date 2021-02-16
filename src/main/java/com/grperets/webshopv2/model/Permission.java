package com.grperets.webshopv2.model;

public enum Permission {

    READ("read"), WRITE("write"), DELETE("delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
