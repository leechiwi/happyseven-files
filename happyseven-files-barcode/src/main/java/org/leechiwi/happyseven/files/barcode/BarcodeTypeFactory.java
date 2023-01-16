package org.leechiwi.happyseven.files.barcode;

import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;

public interface BarcodeTypeFactory<T> {
    T convertBarcode(BarcodeType barcodeType);
}
