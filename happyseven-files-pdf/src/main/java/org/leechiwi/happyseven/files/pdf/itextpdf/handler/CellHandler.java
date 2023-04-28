package org.leechiwi.happyseven.files.pdf.itextpdf.handler;

import com.itextpdf.text.pdf.PdfPCell;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.CellElement;

public interface CellHandler {
    void cell(CellElement cellElement, PdfPCell pdfPCell);
}
