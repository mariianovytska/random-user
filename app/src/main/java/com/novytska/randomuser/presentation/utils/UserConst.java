package com.novytska.randomuser.presentation.utils;

import org.jetbrains.annotations.NotNull;

public enum UserConst {

    PICTURE_PATH("picturePath"),
    FULL_NAME("FullName"),
    GENDER("Gender"),
    DATE("Date"),
    CELL_PHONE("CellPhone"),
    EMAIL("Email");

    private String constValue;

    UserConst(String constValue) {
        this.constValue = constValue;
    }

    @NotNull
    @Override
    public String toString() {
        return constValue;
    }
}
