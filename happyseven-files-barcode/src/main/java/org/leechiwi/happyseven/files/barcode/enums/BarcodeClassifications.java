package org.leechiwi.happyseven.files.barcode.enums;

public enum BarcodeClassifications {
    NONE(0),
    TYPE_1D(1),
    TYPE_2D(2),
    POSTAL(3),
    DATABAR(4),
    COUPON(5);

    private final int a;

    private BarcodeClassifications(int value) {
        this.a = value;
    }

    public int getValue() {
        return this.a;
    }
}
