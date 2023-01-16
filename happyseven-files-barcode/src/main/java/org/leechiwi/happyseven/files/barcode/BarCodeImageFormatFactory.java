package org.leechiwi.happyseven.files.barcode;

import org.leechiwi.happyseven.files.barcode.enums.BarCodeImageFormat;

public interface BarCodeImageFormatFactory<T> {
    T convertBarCodeImageFormat(BarCodeImageFormat barCodeImageFormat);
}

