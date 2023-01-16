package org.leechiwi.happyseven.files.barcode.ap.factory;

import org.leechiwi.happyseven.files.barcode.BarcodeClassificationsFactory;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.util.HashMap;
import java.util.Map;

public class ApBarcodeClassificationsFactory implements BarcodeClassificationsFactory<com.aspose.barcode.generation.BarcodeClassifications> {
    private static final Map<Integer, com.aspose.barcode.generation.BarcodeClassifications> map =new HashMap();
    static{
        com.aspose.barcode.generation.BarcodeClassifications[] values = com.aspose.barcode.generation.BarcodeClassifications.values();
        for (com.aspose.barcode.generation.BarcodeClassifications value : values) {
            map.put(value.getValue(), value);
        }
    }
    @Override
    public com.aspose.barcode.generation.BarcodeClassifications convertBarcodeClassifications(BarcodeClassifications barcodeClassifications) {
        return map.get(barcodeClassifications.getValue());
    }
}
