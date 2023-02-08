package org.leechiwi.happyseven.files.barcode;

import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

public abstract class AbstractBarcode implements Barcode {
    public abstract  boolean doPre();

    public abstract List<String> doBarcodeText(Object image);

    public abstract String doSingleBarcodeText(Object image);

    @Override
    public List<String> getBarcodeText(Object image) {
        if(doPre()){
            return this.doBarcodeText(image);
        }
        return null;
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        if(doPre()){
            return this.doSingleBarcodeText(image);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        return null;
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {

    }
}
