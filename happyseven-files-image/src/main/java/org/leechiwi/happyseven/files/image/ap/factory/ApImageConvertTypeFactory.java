package org.leechiwi.happyseven.files.image.ap.factory;

import com.aspose.imaging.Image;
import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.Size;
import com.aspose.imaging.SizeF;
import com.aspose.imaging.fileformats.pdf.PdfDocumentInfo;
import com.aspose.imaging.imageoptions.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.leechiwi.happyseven.files.image.ImageConvertTypeFactory;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

public class ApImageConvertTypeFactory implements ImageConvertTypeFactory<ImageOptionsBase> {
    private float width;
    private float height;
    private int pages;
    private Image image;

    public ApImageConvertTypeFactory(float width, float height, int pages,Image image) {
        this.width = width;
        this.height = height;
        this.pages = pages;
        this.image=image;
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
            }else{
                float currentImageHeight = this.image.getHeight();
                float currentImageWidth = this.image.getWidth();
                float s1 = PDRectangle.A4.getWidth() / currentImageWidth;
                float s2 = PDRectangle.A4.getHeight() / currentImageHeight;
                if (s1<1 || s2<1) {
                    float scale = 1f;
                    if (s1 > s2){
                        scale = s2;
                    } else {
                        scale = s1;
                    }
                    currentImageWidth=currentImageWidth*scale;
                    currentImageHeight=currentImageHeight*scale;
                }
                pdfOptions.setPageSize(new SizeF(currentImageWidth, currentImageHeight));
            }
            if (this.pages > 0) {
                pdfOptions.setMultiPageOptions(new MultiPageOptions(this.pages));
            }
            return pdfOptions;
        }
        return ImageOptionsBase;
    }
}
