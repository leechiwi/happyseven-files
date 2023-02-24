package org.leechiwi.happyseven.files.doc.ap.decorators;

import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
@Slf4j
public class ApDocImagesDecorator implements Doc<Document> {
    private Doc<Document> apDoc;
    private List<Object> imagesIn;

    public ApDocImagesDecorator(List<Object> imagesIn,Doc<Document> apDoc) {
        this.apDoc = apDoc;
        this.imagesIn=imagesIn;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertTypet) {
        return false;
    }

    @Override
    public boolean convert(File file, OutputStream out, WordConvertType wordConvertType) {
        return false;
    }

    @Override
    public boolean convert(String path, OutputStream out, WordConvertType wordConvertType) {
        return false;
    }

    @Override
    public Document createTextDoc(String text) {
        return this.apDoc.createTextDoc(text);
    }

    @Override
    public Document getDoc() {
        return this.apDoc.getDoc();
    }

    @Override
    public boolean convertAll(OutputStream out, WordConvertType fileConvertType, OptionResult optionResult) {
        try {
            Document doc = getDoc();
            DocumentBuilder builder = new DocumentBuilder(doc);
            for (int i = 0; i < imagesIn.size(); i++) {
                if (i != 0) {
                    builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
                }
                BufferedImage image = new ImageRead().loadImage(imagesIn.get(i));
                double maxPageHeight = PDRectangle.A4.getHeight();//1584
                double maxPageWidth = PDRectangle.A4.getWidth();;
                double currentImageHeight = ConvertUtil.pixelToPoint(image.getHeight());
                double currentImageWidth = ConvertUtil.pixelToPoint(image.getWidth());
                if (currentImageWidth >= maxPageWidth || currentImageHeight >= maxPageHeight)
                {
                    double[] size = CalculateImageSize(image, maxPageHeight, maxPageWidth);
                    currentImageWidth = size[0];
                    currentImageHeight = size[1];
                }
                PageSetup ps = builder.getPageSetup();
                ps.setPageWidth(currentImageWidth);
                ps.setPageHeight(currentImageHeight);
                Shape shape = builder.insertImage(
                        image,
                        RelativeHorizontalPosition.PAGE,
                        0,
                        RelativeVerticalPosition.PAGE,
                        0,
                        ps.getPageWidth(),
                        ps.getPageHeight(),
                        WrapType.NONE);
            }
            this.apDoc.convertAll(out,fileConvertType,optionResult);
        } catch (Exception e) {
            log.error("aspose word images convert to"+ fileConvertType.getExt()+" error",e);
        }
        return true;
    }
    // 等比计算图片尺寸
    public static double[] CalculateImageSize(BufferedImage img, double containerHeight, double containerWidth) throws Exception {
        double targetHeight = containerHeight;
        double targetWidth = containerWidth;
        double imgHeight = ConvertUtil.pixelToPoint(img.getHeight());
        double imgWidth = ConvertUtil.pixelToPoint(img.getWidth());
        if (imgHeight < targetHeight && imgWidth < targetWidth) {
            targetHeight = imgHeight;
            targetWidth = imgWidth;
        } else {
            // 计算文档中图像的大小
            double ratioWidth = imgWidth / targetWidth;
            double ratioHeight = imgHeight / targetHeight;
            if (ratioWidth > ratioHeight) {
                targetHeight = (targetHeight * (ratioHeight / ratioWidth));
            } else {
                targetWidth = (targetWidth * (ratioWidth / ratioHeight));
            }
        }
        double[] size = new double[2];
        size[0] = targetWidth;
        size[1] = targetHeight;
        return (size);
    }
}
