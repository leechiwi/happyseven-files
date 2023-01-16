package org.leechiwi.happyseven.files.barcode.zxing.factory;

import com.google.zxing.BarcodeFormat;
import org.leechiwi.happyseven.files.barcode.BarcodeTypeFactory;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;

public class ZXingBarcodeTypeFactory implements BarcodeTypeFactory<BarcodeFormat> {
    @Override
    public BarcodeFormat convertBarcode(BarcodeType barcodeType) {
        String enumName = barcodeType.toString();
        BarcodeFormat barcodeFormat = BarcodeFormat.valueOf(enumName);
        return barcodeFormat;
    }
}
