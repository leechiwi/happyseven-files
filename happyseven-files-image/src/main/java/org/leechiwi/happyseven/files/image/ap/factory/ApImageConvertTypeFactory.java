package org.leechiwi.happyseven.files.image.ap.factory;

import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.SizeF;
import com.aspose.imaging.fileformats.pdf.PdfDocumentInfo;
import com.aspose.imaging.imageoptions.*;
import org.leechiwi.happyseven.files.image.ImageConvertTypeFactory;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

public class ApImageConvertTypeFactory implements ImageConvertTypeFactory<ImageOptionsBase> {
    private float width;
    private float height;
    private int pages;

    public ApImageConvertTypeFactory(float width, float height, int pages) {
        this.width = width;
        this.height = height;
        this.pages = pages;
    }

    public ApImageConvertTypeFactory() {

    }

    @Override
    public ImageOptionsBase convertImageConvertType(ImageConvertType imageConvertType) {
        ImageOptionsBase ImageOptionsBase = null;
        if (ImageConvertType.JPEG == imageConvertType) {
            ImageOptionsBase = new JpegOptions();
        } else if (ImageConvertType.PNG == imageConvertType) {
            ImageOptionsBase = new PngOptions();
        } else if (ImageConvertType.BMP == imageConvertType) {
            ImageOptionsBase = new BmpOptions();
        } else if (ImageConvertType.GIF == imageConvertType) {
            ImageOptionsBase = new GifOptions();
        } else if (ImageConvertType.TIFF == imageConvertType) {
            ImageOptionsBase = new TiffOptions(15);
        } else if (ImageConvertType.PDF == imageConvertType) {
            PdfOptions pdfOptions = new PdfOptions();
            PdfDocumentInfo pdfDocumentInfo = new PdfDocumentInfo();
            pdfDocumentInfo.setAuthor("");
            pdfDocumentInfo.setKeywords("GIF to PDF");
            pdfOptions.setPdfDocumentInfo(pdfDocumentInfo);
            if (this.width > 0 && this.height > 0) {
                pdfOptions.setPageSize(new SizeF(this.width, this.height));
            }
            if (this.pages > 0) {
                pdfOptions.setMultiPageOptions(new MultiPageOptions(this.pages));
            }
            return pdfOptions;
        }
        return ImageOptionsBase;
    }
}
