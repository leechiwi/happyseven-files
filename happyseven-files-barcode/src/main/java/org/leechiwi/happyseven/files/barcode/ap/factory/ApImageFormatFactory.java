package org.leechiwi.happyseven.files.barcode.ap.factory;

import com.aspose.barcode.generation.BarCodeImageFormat;
import org.leechiwi.happyseven.files.base.ImageFormatFactory;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;

import java.util.HashMap;

public class ApImageFormatFactory implements ImageFormatFactory<BarCodeImageFormat> {
    private static final HashMap<Integer,com.aspose.barcode.generation.BarCodeImageFormat> map = new HashMap();
    static{
        com.aspose.barcode.generation.BarCodeImageFormat[] values = com.aspose.barcode.generation.BarCodeImageFormat.values();
        for (com.aspose.barcode.generation.BarCodeImageFormat value : values) {
            map.put(value.getValue(), value);
        }
    }
    @Override
    public com.aspose.barcode.generation.BarCodeImageFormat convertBarCodeImageFormat(ImageFormat imageFormat) {
        return map.get(imageFormat.getValue());
    }
}
