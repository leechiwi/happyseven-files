package org.leechiwi.happyseven.files.qrcode.enums;

public enum QrcodeType {
    AUTO(0),
    FORCE_QR(1),
    FORCE_MICRO_QR(2);

    private final int a;

    private QrcodeType(int value) {
        this.a = value;
    }

    public int getValue() {
        return this.a;
    }
}
