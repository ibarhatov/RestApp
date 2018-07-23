package ru.ibarhatov.restapp.domain;

public enum RoleCode {
    ADMIN("Администратор"),
    GUEST("Гость"),
    MANAGER("Менеджер"),
    SUPERVISOR("Руководитель"),;

    private String description;

    RoleCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

