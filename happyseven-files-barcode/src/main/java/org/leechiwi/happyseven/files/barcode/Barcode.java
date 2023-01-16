package org.leechiwi.happyseven.files.barcode;

import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.enums.BarCodeImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

public interface Barcode {
    List<String> getBarcodeText(Object image);

    default String getSingleBarcodeText(Object image){
        return StringUtils.EMPTY;
    }

    BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, BarCodeImageFormat barCodeImageFormat);

    void CreateBarcode(String text, OutputStream out);
}
