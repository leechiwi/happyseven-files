package org.leechiwi.happyseven.files.pdf;

import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

public interface PdfConvertTypeFactory<T> {
    T convertPdfConvertType(PdfConvertType pdfConvertType);
}
