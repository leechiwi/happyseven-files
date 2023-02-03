package org.leechiwi.happyseven.files.barcode.ap.factory;

import org.leechiwi.happyseven.files.barcode.BarcodeClassificationsFactory;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;

import java.util.HashMap;
import java.util.Map;

public class ApBarcodeClassificationsFactory implements BarcodeClassificationsFactory<com.aspose.barcode.BarcodeClassifications> {
    private static final Map<Integer, com.aspose.barcode.BarcodeClassifications> map =new HashMap();
    static{
        com.aspose.barcode.BarcodeClassifications[] values = com.aspose.barcode.BarcodeClassifications.values();
        for (com.aspose.barcode.BarcodeClassifications value : values) {
            map.put(value.getValue(), value);
        }
    }
    @Override
    public com.aspose.barcode.BarcodeClassifications convertBarcodeClassifications(BarcodeClassifications barcodeClassifications) {
        return map.get(barcodeClassifications.getValue());
    }
}
