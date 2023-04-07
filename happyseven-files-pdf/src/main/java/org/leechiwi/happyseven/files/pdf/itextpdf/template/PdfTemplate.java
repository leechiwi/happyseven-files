package org.leechiwi.happyseven.files.pdf.itextpdf.template;

import java.io.OutputStream;

public interface PdfTemplate {
    boolean create(OutputStream out);
}
