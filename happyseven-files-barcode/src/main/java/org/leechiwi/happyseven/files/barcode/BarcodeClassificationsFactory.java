package org.leechiwi.happyseven.files.barcode;

import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

public interface BarcodeClassificationsFactory<T> {
    T convertBarcodeClassifications(BarcodeClassifications barcodeClassifications);
}
