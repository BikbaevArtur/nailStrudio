package com.bikbaev.nailstudio.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum AppointmentStatus implements EnumClass<String> {

    NEW("new"),
    CONFIRMENT("confirment"),
    COMPLETED("completed");

    private final String id;

    AppointmentStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static AppointmentStatus fromId(String id) {
        for (AppointmentStatus at : AppointmentStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}