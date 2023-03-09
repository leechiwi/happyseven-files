package org.leechiwi.happyseven.files.image.ap.proxy;

import org.apache.commons.collections.CollectionUtils;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImagesDecorator;
import org.leechiwi.happyseven.files.image.Image;
import org.leechiwi.happyseven.files.image.ap.ApImage;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;
import org.leechiwi.happyseven.files.pdf.ap.ApPdf;
import org.leechiwi.happyseven.files.pdf.ap.decorators.ApPdfImagesDecorator;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ApImageProxy<T extends Enum> implements Image {
    private org.leechiwi.happyseven.files.base.File file;
    private ImagesType imagesType;
    private Class<T> clazz;

    public ApImageProxy(ImageFormat sourceImageformat, List<Object> imagesIn, int width, int height, ImagesType imagesType) {
        if (CollectionUtils.isNotEmpty(imagesIn)) {
            imagesType.imagesBy(this, imagesIn, sourceImageformat, width, height);
        }
        this.imagesType=imagesType;
    }

    public ApImageProxy(ImageFormat sourceImageformat, List<Object> imagesIn) {
        this(sourceImageformat, imagesIn,  ImagesType.IMAGE);
    }

    public ApImageProxy(ImageFormat sourceImageformat, List<Object> imagesIn, ImagesType imagesType) {
        this(sourceImageformat, imagesIn, 0, 0, imagesType);
    }

    public void createByWordFile(List<Object> imagesIn, Class<T> clazz, ImageFormat sourceImageformat, int width, int height) {
        this.file = new ApDocImagesDecorator(imagesIn, new ApDoc(null));
        this.clazz = clazz;
    }

    public void createByPdfFile(List<Object> imagesIn, Class<T> clazz, ImageFormat sourceImageformat, int width, int height) {
        this.file = new ApPdfImagesDecorator(imagesIn, new ApPdf(null));
        this.clazz = clazz;
    }

    public void createByImage(List<Object> imagesIn, Class<T> clazz, ImageFormat sourceImageformat, int width, int height) {
        this.file = new ApImage(sourceImageformat, imagesIn.get(0), width, height);
        this.clazz = clazz;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, ImageConvertType imageConvertType) {
        return imagesType.convert(this.file,in, out, imageConvertType);
    }

    @Override
    public boolean convert(String in, OutputStream out, ImageConvertType imageConvertType) {
        return imagesType.convert(this.file,in, out, imageConvertType);
    }

    @Override
    public boolean convert(File in, OutputStream out, ImageConvertType imageConvertType) {
        return imagesType.convert(this.file,in, out, imageConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, ImageConvertType fileConvertType, OptionResult optionResult) {
        return  this.file.convertAll(out, T.valueOf(this.clazz, fileConvertType.toString()), optionResult);
        /*if(manyImage){
            //WordConvertType wordConvertType = WordConvertType.valueOf(fileConvertType.toString());
            result=this.file.convertAll(out, T.valueOf(clazz, fileConvertType.toString()),optionResult);
        }else{
            result=this.image.convertAll(out, fileConvertType, optionResult);
        }*/
    }
}
