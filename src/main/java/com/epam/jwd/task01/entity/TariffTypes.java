package com.epam.jwd.task01.entity;

/**
 * Enumeration to determine the possible types of tariffs
 */

public enum TariffTypes {
    BASE("BASE"),
    CORPORATE("CORPORATE");

    private final String type;

    private TariffTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
