package org.leechiwi.happyseven.files.base.enums;

public enum ResultOptions {
    ALL_IN_ZIP(0),
    MANY(1),
    NONE(2),
    ALL_TO_PATH(3);

    private final int a;

    private ResultOptions(int value){
        this.a=value;
    }
    public int getValue() {
        return this.a;
    }
}
