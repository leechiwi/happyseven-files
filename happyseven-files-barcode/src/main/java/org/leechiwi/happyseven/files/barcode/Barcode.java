package org.leechiwi.happyseven.files.barcode;

import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface Barcode {
    List<String> getBarcodeText(Object image, SingleDecodeType codeType);
    default String getSingleBarcodeText(Object image, SingleDecodeType codeType){
        return StringUtils.EMPTY;
    }
}
