package org.leechiwi.happyseven.files.pdf.ap.proxy;

import com.aspose.pdf.Document;
import com.aspose.pdf.IDocument;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.base.util.Result;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.ap.ApPdf;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApPdfInnerImagesProxy implements Pdf<Document> {
    private Object in;
    private Pdf<Document> apPdf;
    private ResultOptions resultOptions;
    private boolean parseImages;

    public ApPdfInnerImagesProxy(Object in, ResultOptions resultOptions, boolean parseImages) {
        if (Objects.nonNull(in)) {
            this.apPdf = new ApPdf(in);
        } else {
            this.apPdf = new ApPdf();
        }
        this.in = in;
        this.resultOptions = resultOptions;
        this.parseImages = parseImages;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        return this.apPdf.convert(in, out, pdfConvertType);
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        return this.apPdf.convert(file, out, pdfConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        return this.apPdf.convert(path, out, pdfConvertType);
    }

    @Override
    public Document getDoc() {
        return this.apPdf.getDoc();
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        if(parseImages){
            return this.parseInnerImages(out, fileConvertType, optionResult);
        }
        return this.apPdf.convertAll(out,fileConvertType,optionResult);
    }

    private boolean parseInnerImages(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        List<byte[]> list = new ArrayList<>();
        // Bind a PDF document
        com.aspose.pdf.facades.PdfExtractor pdfExtractor = new com.aspose.pdf.facades.PdfExtractor();
        InputStream inputStream = new FileRead().loadFile(in);
        pdfExtractor.bindPdf(inputStream);
        IDocument document = pdfExtractor.getDocument();
        int size = document.getPages().size();
        // Set page range for image extraction
        pdfExtractor.setStartPage(1);
        pdfExtractor.setEndPage(size);
        // Extract the images
        pdfExtractor.extractImage();
        int imageCount = 1;
        // Save images to stream in a loop
        while (pdfExtractor.hasNextImage()) {
            // Save image
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            pdfExtractor.getNextImage(output);
            list.add(output.toByteArray());
        }
        Result.convertToImageResult(resultOptions, PdfConvertType.JPEG.getExt(), out, list, optionResult);
        return true;
    }
}
