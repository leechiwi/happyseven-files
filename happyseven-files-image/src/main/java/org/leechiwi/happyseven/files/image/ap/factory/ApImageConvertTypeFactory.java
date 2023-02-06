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

    public ApImageConvertTypeFactory(float width, float height) {
        this.width = width;
        this.height = height;
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
            pdfOptions.setPdfDocumentInfo(new PdfDocumentInfo());
            if (this.width > 0 && this.height > 0) {
                pdfOptions.setPageSize(new SizeF(this.width, this.height));
            }
            return pdfOptions;
        }
        return ImageOptionsBase;
    }
}
