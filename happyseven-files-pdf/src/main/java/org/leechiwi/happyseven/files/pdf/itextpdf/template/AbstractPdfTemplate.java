package org.leechiwi.happyseven.files.pdf.itextpdf.template;

import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractPdfTemplate implements PdfTemplate {
    protected InputStream inputStream;
    protected PdfTemplateElement pdfTemplateElement;
    public AbstractPdfTemplate(){

    }

    public PdfTemplateElement getPdfTemplateElement() {
        return pdfTemplateElement;
    }

    public AbstractPdfTemplate(InputStream inputStream, PdfTemplateElement pdfTemplateElement) {
        this.inputStream = inputStream;
        this.pdfTemplateElement = pdfTemplateElement;
    }

    protected abstract boolean doCreate(OutputStream out);
    @Override
    public boolean create(OutputStream out) {
        return doCreate(out);
    }
}
