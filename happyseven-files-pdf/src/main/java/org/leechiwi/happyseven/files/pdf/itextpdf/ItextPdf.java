package org.leechiwi.happyseven.files.pdf.itextpdf;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfTemplateType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class ItextPdf implements Pdf<InputStream> {
    private InputStream inputStream;
    private PdfTemplateElement pdfTemplateElement;
    private PdfTemplateType pdfTemplateType;

    public ItextPdf(Object in,PdfTemplateElement pdfTemplateElement,PdfTemplateType pdfTemplateType) {
        this.inputStream = createDocument(in);
        this.pdfTemplateElement=pdfTemplateElement;
        this.pdfTemplateType=pdfTemplateType;
    }
    private InputStream createDocument(Object in) {
        InputStream inputStream=null;
        try {
            inputStream = new FileRead().loadFile(in);
        } catch (Exception e) {
            log.error("itextpdf Document error", e);
        }
        return inputStream;
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        return this.pdfTemplateType.doPdfTemplate(in,out,this.pdfTemplateElement);
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream inputStream = this.createDocument(file);
        return convert(inputStream,out,pdfConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream inputStream = this.createDocument(path);
        return convert(inputStream,out,pdfConvertType);
    }

    @Override
    public InputStream getDoc() {
        return this.inputStream;
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        return this.pdfTemplateType.doPdfTemplate(this.inputStream,out,this.pdfTemplateElement);
    }

}
