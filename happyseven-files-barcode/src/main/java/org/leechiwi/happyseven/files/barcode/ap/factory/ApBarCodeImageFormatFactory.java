package org.leechiwi.happyseven.files.barcode.ap.factory;

import org.leechiwi.happyseven.files.barcode.BarCodeImageFormatFactory;
import org.leechiwi.happyseven.files.barcode.enums.BarCodeImageFormat;

import java.util.HashMap;

public class ApBarCodeImageFormatFactory implements BarCodeImageFormatFactory<com.aspose.barcode.generation.BarCodeImageFormat> {
    private static final HashMap<Integer,com.aspose.barcode.generation.BarCodeImageFormat> map = new HashMap();
    static{
        com.aspose.barcode.generation.BarCodeImageFormat[] values = com.aspose.barcode.generation.BarCodeImageFormat.values();
        for (com.aspose.barcode.generation.BarCodeImageFormat value : values) {
            map.put(value.getValue(), value);
        }
    }
    @Override
    public com.aspose.barcode.generation.BarCodeImageFormat convertBarCodeImageFormat(BarCodeImageFormat barCodeImageFormat) {
        return map.get(barCodeImageFormat.getValue());
    }
}
