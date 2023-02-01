package org.leechiwi.happyseven.files.barcode;

import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

public abstract class AbstractBarcode implements Barcode {
    public abstract  boolean pre();
    public List<String> getPreBarcodeText(Object image) {
        if(pre()){
           return this.getBarcodeText(image);
        }
        return null;
    }

    public String getPreSingleBarcodeText(Object image,Barcode barcodeImpl) {
        if(pre()){
            return barcodeImpl.getSingleBarcodeText(image);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<String> getBarcodeText(Object image) {
        return null;
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        return null;
    }

    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        return null;
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {

    }
}
