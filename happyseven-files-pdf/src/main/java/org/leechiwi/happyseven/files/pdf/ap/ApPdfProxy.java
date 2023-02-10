package org.leechiwi.happyseven.files.pdf.ap;

import com.aspose.pdf.PageCollection;
import com.aspose.pdf.devices.*;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.base.util.Zip;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
public class ApPdfProxy implements Pdf {
    private int dpi;
    private ApPdf apPdf;
    private ResultOptions resultOptions;

    public ApPdfProxy(int dpi, Object in,ResultOptions resultOptions) {
        if (Objects.nonNull(in)) {
            this.apPdf = new ApPdf(in);
        } else {
            this.apPdf = new ApPdf();
        }
        this.dpi = dpi;
        this.resultOptions=resultOptions;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        if(toImage(pdfConvertType)){
            return false;
        }
        return this.apPdf.convert(in, out, pdfConvertType);
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        if(toImage(pdfConvertType)){
            return false;
        }
        return this.apPdf.convert(file, out, pdfConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        if(toImage(pdfConvertType)){
            return false;
        }
        return this.apPdf.convert(path, out, pdfConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType pdfConvertType, OptionResult optionResult) {
        if(toImage(pdfConvertType)){
            return convertToImg(pdfConvertType,out,optionResult);
        }
        return this.apPdf.convertAll(out, pdfConvertType,optionResult);
    }

    private boolean toImage(PdfConvertType pdfConvertType) {
        return PdfConvertType.getImageTypeSet().contains(pdfConvertType);
    }

    private ImageDevice getImageDevice(PdfConvertType pdfConvertType) {
        ImageDevice imageDevice;
        Resolution resolution = new Resolution(this.dpi);
        switch (pdfConvertType) {
            case JPEG:
                imageDevice = new JpegDevice(resolution);
                break;
            case PNG:
                imageDevice = new PngDevice(resolution);
                break;
            case GIF:
                imageDevice = new GifDevice(resolution);
                break;
            case BMP:
                imageDevice = new BmpDevice(resolution);
                break;
            default:
                imageDevice = null;
        }
        return imageDevice;
    }
    private boolean convertToImg(PdfConvertType pdfConvertType, OutputStream out, OptionResult optionResult) {
        try {
            List<byte[]> list = new ArrayList<>();
            ImageDevice imageDevice = getImageDevice(pdfConvertType);
            PageCollection pages = this.apPdf.getDocument().getPages();
            for (int i = 0, size = pages.size(); i < size; i++) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                imageDevice.process(pages.get_Item(i+1), os);
                list.add(os.toByteArray());
            }

            if (ResultOptions.ALL_IN_ZIP == this.resultOptions) {
                Zip.zip(out,list,pdfConvertType.getExt());
                if (out instanceof ByteArrayOutputStream) {
                    ArrayList<byte[]> lst = new ArrayList<>();
                    lst.add(((ByteArrayOutputStream) out).toByteArray());
                    optionResult.setByteList(lst);
                }
            }else if(ResultOptions.MANY == this.resultOptions){
                optionResult.setByteList(list);
            }
        } catch (Exception e) {
            log.error("aspose pdf convert image file error",e);
            return false;
        }
        return true;
    }
}
