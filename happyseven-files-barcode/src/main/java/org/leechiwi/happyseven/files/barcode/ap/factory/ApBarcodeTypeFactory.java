package org.leechiwi.happyseven.files.barcode.ap.factory;

import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.BarcodeTypeFactory;

public class ApBarcodeTypeFactory implements BarcodeTypeFactory<SingleDecodeType> {
    @Override
    public SingleDecodeType convertBarcode(BarcodeType barcodeType) {
        return new SingleDecodeType(barcodeType.getCode(), barcodeType.getName());
    }
}
