package org.leechiwi.happyseven.files.barcode;

import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BaseEncodeType;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

public interface Barcode {
    List<String> getBarcodeText(Object image, SingleDecodeType codeType);

    default String getSingleBarcodeText(Object image, SingleDecodeType codeType){
        return StringUtils.EMPTY;
    }

    BufferedImage CreateBarcode(String text, BaseEncodeType baseEncodeType, OutputStream out, BarCodeImageFormat barCodeImageFormat);

    void CreateBarcode(String text, BaseEncodeType baseEncodeType,OutputStream out);
}
