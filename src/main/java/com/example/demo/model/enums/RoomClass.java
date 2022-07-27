package com.example.demo.model.enums;

import java.util.Locale;

public enum RoomClass {
    ECONOMY,
    STANDARD,
    JUNIOR_SUITE,
    SUITE;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().toLowerCase(Locale.ROOT).substring(1).replace('_',' ');
    }
}
