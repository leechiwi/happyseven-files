package org.leechiwi.happyseven.files.image.ap.factory;

import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.fileformats.pdf.PdfDocumentInfo;
import com.aspose.imaging.imageoptions.*;
import org.leechiwi.happyseven.files.image.ImageConvertTypeFactory;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

public class ApImageConvertTypeFactory implements ImageConvertTypeFactory<ImageOptionsBase> {
    @Override
    public ImageOptionsBase convertImageConvertType(ImageConvertType imageConvertType) {
        ImageOptionsBase ImageOptionsBase=null;
        if(ImageConvertType.JPEG==imageConvertType){
            ImageOptionsBase= new JpegOptions();
        }else if(ImageConvertType.PNG==imageConvertType){
            ImageOptionsBase= new PngOptions();
        }else if(ImageConvertType.BMP==imageConvertType){
            ImageOptionsBase= new BmpOptions();
        }else if(ImageConvertType.GIF==imageConvertType){
            ImageOptionsBase= new GifOptions();
        }else if(ImageConvertType.TIFF==imageConvertType){
            ImageOptionsBase= new TiffOptions(15);
        }else if(ImageConvertType.PDF==imageConvertType){
            PdfOptions pdfOptions = new PdfOptions();
            pdfOptions.setPdfDocumentInfo(new PdfDocumentInfo());
            return pdfOptions;
        }
        return ImageOptionsBase;
    }
}
