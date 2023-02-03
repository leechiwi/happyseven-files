package org.leechiwi.happyseven.files.qrcode.ap.factory;

import com.aspose.barcode.QREncodeType;
import org.leechiwi.happyseven.files.qrcode.QrcodeTypeFactory;
import org.leechiwi.happyseven.files.qrcode.enums.QrcodeType;

public class ApQrcodeTypeFactory implements QrcodeTypeFactory<QREncodeType> {
    @Override
    public QREncodeType convertBarcode(QrcodeType qrcodeType) {
        String type = qrcodeType.toString();
        QREncodeType qrEncodeType = QREncodeType.valueOf(type);
        return qrEncodeType;
    }
}
