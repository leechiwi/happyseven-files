package org.leechiwi.happyseven.files.barcode.ap.encodetype;

import com.aspose.barcode.BarcodeClassifications;
import com.aspose.barcode.BaseEncodeType;

public class CustomEncodeType extends BaseEncodeType {
    public CustomEncodeType(short typeIndex, String typeName, BarcodeClassifications classification){
        super(typeIndex, typeName, classification);
    }
}
