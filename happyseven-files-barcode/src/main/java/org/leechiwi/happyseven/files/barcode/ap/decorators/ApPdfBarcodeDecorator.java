package org.leechiwi.happyseven.files.barcode.ap.decorators;

import com.aspose.pdf.Document;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfInnerImagesProxy;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class ApPdfBarcodeDecorator implements Barcode {
    private Pdf<Document> apPdf;
    private Barcode apBarcode;

    public ApPdfBarcodeDecorator(Object in, Barcode apBarcode) {
        this.apPdf = new ApPdfInnerImagesProxy(in, ResultOptions.MANY, true);
        this.apBarcode = apBarcode;
    }

    @Override
    public List<String> getBarcodeText(Object image) {
        return this.apBarcode.getBarcodeText(image);
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        return this.apBarcode.getSingleBarcodeText(image);
    }

    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        return this.apBarcode.CreateBarcode(text, out, barcodeClassifications, imageFormat);
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {
        this.apBarcode.CreateBarcode(text, out);
    }

    @Override
    public List<String> getBarcodeTextAsync(ThreadPoolExecutor pool, List<Object> imageList, int thread) {
        OptionResult optionResult = new OptionResult();
        OutputStream out=null;
        boolean result = this.apPdf.convertAll(out, PdfConvertType.JPEG, optionResult);
        if(!result){
            return null;
        }
        return this.apBarcode.getBarcodeTextAsync(pool,optionResult.getList(),thread);
    }
}
