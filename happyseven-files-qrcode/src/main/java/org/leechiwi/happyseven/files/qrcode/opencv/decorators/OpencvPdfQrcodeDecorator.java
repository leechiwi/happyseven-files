package org.leechiwi.happyseven.files.qrcode.opencv.decorators;

import com.aspose.pdf.Document;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfInnerImagesProxy;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;
import org.leechiwi.happyseven.files.qrcode.Qrcode;

import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class OpencvPdfQrcodeDecorator implements Qrcode {
    private Pdf<Document> apPdf;
    private Qrcode opencvQrcode;

    public OpencvPdfQrcodeDecorator(Object in, Qrcode opencvQrcode) {
        this.apPdf = new ApPdfInnerImagesProxy(in, ResultOptions.MANY,true);
        this.opencvQrcode= opencvQrcode;
    }

    @Override
    public List<String> getQrcodeText(Object image) {
        return this.opencvQrcode.getQrcodeText(image);
    }

    @Override
    public String getSingleQrcodeText(Object image) {
        return this.opencvQrcode.getSingleQrcodeText(image);
    }

    @Override
    public boolean createQrcode(String text, OutputStream out) {
        return this.opencvQrcode.createQrcode(text,out);
    }

    @Override
    public List<String> getQrcodeTextAsync(ThreadPoolExecutor pool, List<Object> imageList, int thread) {
        OptionResult optionResult = new OptionResult();
        OutputStream out=null;
        boolean result = this.apPdf.convertAll(out, PdfConvertType.JPEG, optionResult);
        if(!result){
            return null;
        }
        return opencvQrcode.getQrcodeTextAsync(pool, optionResult.getList(), thread);
    }
    public List<String> getQrcodeTextAsync(ThreadPoolExecutor pool, int thread) {
        return getQrcodeTextAsync(pool,null, thread);
    }
}
