package org.leechiwi.happyseven.files.barcode;

import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface Barcode {
    List<String> getBarcodeText(Object image);

    default String getSingleBarcodeText(Object image) {
        return StringUtils.EMPTY;
    }

    BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat);

    void CreateBarcode(String text, OutputStream out);

    default List<String> getBarcodeTextAsync(ThreadPoolExecutor pool, List<Object> imageList, int thread) {
        return null;
    }

}
