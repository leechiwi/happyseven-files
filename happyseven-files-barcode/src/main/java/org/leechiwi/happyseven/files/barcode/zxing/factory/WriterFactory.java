package org.leechiwi.happyseven.files.barcode.zxing.factory;

import com.google.zxing.Writer;
import com.google.zxing.oned.*;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
@Slf4j
public class WriterFactory {
    public Writer createWriter(BarcodeType barcodeType) {
        Writer writer = null;
        String name = barcodeType.getName();
        if ("Code128".equals(name)) {
            writer = new Code128Writer();
        } else if ("Codabar".equals(name)) {
            writer = new CodaBarWriter();
        } else if ("Code39Standard".equals(name)) {
            writer = new Code39Writer();
        } else if ("Code93Standard".equals(name)) {
            writer = new Code93Writer();
        } else if ("EAN8".equals(name)) {
            writer = new EAN8Writer();
        } else if ("EAN13".equals(name)) {
            writer = new EAN13Writer();
        } else if ("ITF14".equals(name)) {
            writer = new ITFWriter();
        } else if ("UPCA".equals(name)) {
            writer = new UPCAWriter();
        } else if ("UPCE".equals(name)) {
            writer = new UPCEWriter();
        } else {
           log.error("unsupport barcodeType for ZXing");
        }
        return writer;
    }
}
