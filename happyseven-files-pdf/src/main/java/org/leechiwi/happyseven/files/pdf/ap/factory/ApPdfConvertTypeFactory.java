package org.leechiwi.happyseven.files.pdf.ap.factory;

import org.leechiwi.happyseven.files.pdf.PdfConvertTypeFactory;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

public class ApPdfConvertTypeFactory implements PdfConvertTypeFactory<Integer> {
    @Override
    public Integer convertPdfConvertType(PdfConvertType pdfConvertType) {
        return pdfConvertType.getCode();
    }
}
