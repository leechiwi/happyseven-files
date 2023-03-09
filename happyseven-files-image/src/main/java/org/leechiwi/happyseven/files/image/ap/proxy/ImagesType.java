package org.leechiwi.happyseven.files.image.ap.proxy;

import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImagesDecorator;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.image.ap.ApImage;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;
import org.leechiwi.happyseven.files.pdf.ap.decorators.ApPdfImagesDecorator;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public enum ImagesType {
    PDF{
        public void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn, ImageFormat sourceImageformat, int width, int height){
            apImageProxy.createByPdfFile(imagesIn, PdfConvertType.class,sourceImageformat,width,height);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, InputStream in, OutputStream out, ImageConvertType imageConvertType) {
            PdfConvertType pdfConvertType = PdfConvertType.valueOf(imageConvertType.toString());
            return ((ApPdfImagesDecorator)fileObject).convert(in,out,pdfConvertType);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, String in, OutputStream out, ImageConvertType imageConvertType) {
            PdfConvertType pdfConvertType = PdfConvertType.valueOf(imageConvertType.toString());
            return ((ApPdfImagesDecorator)fileObject).convert(in,out,pdfConvertType);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, File in, OutputStream out, ImageConvertType imageConvertType) {
            PdfConvertType pdfConvertType = PdfConvertType.valueOf(imageConvertType.toString());
            return ((ApPdfImagesDecorator)fileObject).convert(in,out,pdfConvertType);
        }
    },
    WORD{
        public void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn, ImageFormat sourceImageformat, int width, int height){
            apImageProxy.createByWordFile(imagesIn, WordConvertType.class,sourceImageformat,width,height);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, InputStream in, OutputStream out, ImageConvertType imageConvertType) {
            WordConvertType wordConvertType = WordConvertType.valueOf(imageConvertType.toString());
            return ((ApDocImagesDecorator)fileObject).convert(in,out,wordConvertType);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, String in, OutputStream out, ImageConvertType imageConvertType) {
            WordConvertType wordConvertType = WordConvertType.valueOf(imageConvertType.toString());
            return ((ApDocImagesDecorator)fileObject).convert(in,out,wordConvertType);
        }

        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, File in, OutputStream out, ImageConvertType imageConvertType) {
            WordConvertType wordConvertType = WordConvertType.valueOf(imageConvertType.toString());
            return ((ApDocImagesDecorator)fileObject).convert(in,out,wordConvertType);
        }
    },
    IMAGE{
        public void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn, ImageFormat sourceImageformat, int width, int height){
            apImageProxy.createByImage(imagesIn, ImageConvertType.class,sourceImageformat,width,height);
        }

        @Override
        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, InputStream in, OutputStream out, ImageConvertType imageConvertType) {
            return ((ApImage)fileObject).convert(in,out,imageConvertType);
        }

        @Override
        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, String in, OutputStream out, ImageConvertType imageConvertType) {
            return ((ApImage)fileObject).convert(in,out,imageConvertType);
        }

        @Override
        public boolean convert(org.leechiwi.happyseven.files.base.File fileObject, File in, OutputStream out, ImageConvertType imageConvertType) {
            return ((ApImage)fileObject).convert(in,out,imageConvertType);
        }
    };
    public abstract void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn, ImageFormat sourceImageformat, int width, int height);

    public abstract boolean convert(org.leechiwi.happyseven.files.base.File fileObject,InputStream in, OutputStream out, ImageConvertType imageConvertType);

    public abstract boolean convert(org.leechiwi.happyseven.files.base.File fileObject,String in, OutputStream out, ImageConvertType imageConvertType);

    public abstract boolean convert(org.leechiwi.happyseven.files.base.File fileObject,File in, OutputStream out, ImageConvertType imageConvertType);
}
