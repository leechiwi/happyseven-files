package org.leechiwi.happyseven.files.barcode.ap.factory;

import com.aspose.barcode.BarCodeImageFormat;
import org.leechiwi.happyseven.files.base.ImageFormatFactory;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;

import java.util.HashMap;

public class ApImageFormatFactory implements ImageFormatFactory<BarCodeImageFormat> {
    private static final HashMap<Integer,BarCodeImageFormat> map = new HashMap();
    static{
        BarCodeImageFormat[] values =BarCodeImageFormat.values();
        for (BarCodeImageFormat value : values) {
            map.put(value.getValue(), value);
        }
    }
    @Override
    public BarCodeImageFormat convertBarCodeImageFormat(ImageFormat imageFormat) {
        return map.get(imageFormat.getValue());
    }
}
