package org.leechiwi.happyseven.files.pdf.ap;

import com.aspose.pdf.Document;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.ap.factory.ApPdfConvertTypeFactory;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.*;
import java.util.Objects;

@Slf4j
public class ApPdf extends PdfLicense implements Pdf {
    private Document document;

    public ApPdf(Object in) {
        if(Objects.nonNull(in)) {
            this.document = createDocument(in);
        }
    }

    public ApPdf() {

    }

    public Document getDocument() {
        return document;
    }

    private Document createDocument(Object document){
        Document doc=null;
        try {
            InputStream inputStream = new FileRead().loadFile(document);
            doc = new Document(inputStream);
        } catch (Exception e) {
            log.error("create aspose pdf Document error", e);
            //throw new HappysevenException("create aspose word Document error", e);
        }
        return doc;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        try {
            Document document = new Document(in);
            document.save(out, new ApPdfConvertTypeFactory().convertPdfConvertType(pdfConvertType));
        }catch(Exception e){
            log.error("aspose pdf convert stream error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("aspose pdf convert file error", e);
            return false;
        }
        return convert(in,out,pdfConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            log.error("aspose pdf convert filepath error", e);
        }
        return convert(in,out,pdfConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType pdfConvertType, OptionResult optionResult) {
        try {
            document.save(out, new ApPdfConvertTypeFactory().convertPdfConvertType(pdfConvertType));
        } catch (Exception e) {
            log.error("aspose pdf convert all error", e);
            return false;
        }
        return true;
    }
}
