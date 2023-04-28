package org.leechiwi.happyseven.files.pdf.itextpdf.handler;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.CellElement;

public class CellEvent implements PdfPCellEvent {
    private CellElement cellElement;

    public CellEvent(CellElement cellElement) {
        this.cellElement = cellElement;
    }

    @Override
    public void cellLayout(PdfPCell pdfPCell, Rectangle rectangle, PdfContentByte[] pdfContentBytes) {
       
    }
}
