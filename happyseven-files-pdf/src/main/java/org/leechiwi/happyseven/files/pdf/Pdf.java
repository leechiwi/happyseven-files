package org.leechiwi.happyseven.files.pdf;

import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Pdf {
    boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType);

    boolean convert(File file, OutputStream out,  PdfConvertType pdfConvertType);

    boolean convert(String path, OutputStream out,  PdfConvertType pdfConvertType);

    boolean convertAll(OutputStream out,  PdfConvertType pdfConvertType);
}
