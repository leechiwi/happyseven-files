package org.leechiwi.happyseven.files.pdf.itextpdf.template.enums;

import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.PdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.TablePdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.TextPdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.proxy.TablePdfTemplateProxy;

import java.io.InputStream;
import java.io.OutputStream;

public enum PdfTemplateType {
    PURE_TEXT{
        public  boolean doPdfTemplate(InputStream inputStream, OutputStream out,PdfTemplateElement pdfTemplateElement){
            PdfTemplate pdfTemplate=new TextPdfTemplate(inputStream,pdfTemplateElement);
            return pdfTemplate.create(out);
        }
    },
    TABLE{
        public  boolean doPdfTemplate(InputStream inputStream, OutputStream out,PdfTemplateElement pdfTemplateElement){
            PdfTemplate pdfTemplate=new TablePdfTemplate(inputStream,pdfTemplateElement);
            return pdfTemplate.create(out);
        }
    },
    OBJECT_TABLE{
        public  boolean doPdfTemplate(InputStream inputStream, OutputStream out,PdfTemplateElement pdfTemplateElement){
            PdfTemplate pdfTemplate=new TablePdfTemplateProxy(inputStream,pdfTemplateElement);
            return pdfTemplate.create(out);
        }
    };
    public abstract boolean doPdfTemplate(InputStream inputStream, OutputStream out,PdfTemplateElement pdfTemplateElement);
}
