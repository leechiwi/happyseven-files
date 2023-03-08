package org.leechiwi.happyseven.files.pdf.ap.decorators;

import com.aspose.pdf.Document;
import com.aspose.pdf.Image;
import com.aspose.pdf.Page;
import com.aspose.pdf.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
@Slf4j
public class ApPdfImagesDecorator implements Pdf<Document> {
    private Pdf<Document> apPdf;
    private List<Object> imagesIn;

    public ApPdfImagesDecorator(List<Object> imagesIn, Pdf<Document> apPdf) {
        this.apPdf = apPdf;
        this.imagesIn = imagesIn;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        return false;
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        return false;
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        return false;
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        boolean result=true;
        Document document=getDoc();
        try {
            for (int i = 0; i < imagesIn.size(); i++) {
                BufferedImage bufferedImage = new ImageRead().loadImage(imagesIn.get(i));
                double currentImageHeight = bufferedImage.getHeight();
                double currentImageWidth = bufferedImage.getWidth();
                double s1 = PDRectangle.A4.getWidth() / currentImageWidth;
                double s2 = PDRectangle.A4.getHeight() / currentImageHeight;
                if (s1<1 || s2<1) {
                    double scale = 1d;
                    if (s1 > s2){
                        scale = s2;
                    } else {
                        scale = s1;
                    }
                    currentImageWidth=currentImageWidth*scale;
                    currentImageHeight=currentImageHeight*scale;
                }
                Page page=document.getPages().add();
                page.getPageInfo().getMargin().setTop(0);
                page.getPageInfo().getMargin().setRight(0);
                page.getPageInfo().getMargin().setBottom(0);
                page.getPageInfo().getMargin().setLeft(0);
                page.setCropBox(new Rectangle(0,0,currentImageWidth,currentImageHeight));
                Image image = new Image();
                image.setBufferedImage(bufferedImage);
                page.getParagraphs().add(image);
            }
            result= this.apPdf.convertAll(out,fileConvertType,optionResult);
        } catch (Exception e) {
           log.error("aspose pdf images convert to"+ fileConvertType.getExt()+" error",e);
            result= false;
        }
        return result;
    }

    @Override
    public Document getDoc() {
        return this.apPdf.getDoc();
    }
}
