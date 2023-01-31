package org.leechiwi.happyseven.files.qrcode;

import org.leechiwi.happyseven.files.qrcode.enums.QrcodeType;

public interface QrcodeTypeFactory<T> {
    T convertBarcode(QrcodeType qrcodeType);
}
