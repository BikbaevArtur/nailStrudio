package com.bikbaev.nailstudio.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum FreeEntriesStatus implements EnumClass<String> {

    FREE("free"),
    OCCUPIED("occupied");

    private final String id;

    FreeEntriesStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static FreeEntriesStatus fromId(String id) {
        for (FreeEntriesStatus at : FreeEntriesStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}