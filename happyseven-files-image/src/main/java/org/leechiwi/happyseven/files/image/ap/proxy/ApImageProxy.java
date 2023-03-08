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
    private Image image;
    private boolean manyImage;
    private Class<T> clazz;

    public ApImageProxy(ImageFormat sourceImageformat, List<Object> imagesIn, int width, int height, ImagesType imagesType) {
        if(CollectionUtils.isNotEmpty(imagesIn)){
            if(imagesIn.size()>1){
                this.manyImage=true;
                imagesType.imagesBy(this,imagesIn);
            }else{
                image=new ApImage(sourceImageformat,imagesIn.get(0),width,height);
            }
        }
    }
    public ApImageProxy(ImageFormat sourceImageformat, List<Object> imagesIn, int width, int height){
        this(sourceImageformat,imagesIn,width,height,ImagesType.PDF);
    }
    public void createByWordFile(List<Object> imagesIn,Class<T> clazz){
        this.file = new ApDocImagesDecorator(imagesIn, new ApDoc(null));
        this.clazz=clazz;
    }
    public void createByPdfFile(List<Object> imagesIn,Class<T> clazz){
        this.file = new ApPdfImagesDecorator(imagesIn, new ApPdf(null));
        this.clazz=clazz;
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, ImageConvertType imageConvertType) {
        boolean result=true;
        if(manyImage){

        }else{
            result=this.image.convert(in, out, imageConvertType);
        }
        return result;
    }

    @Override
    public boolean convert(String in, OutputStream out, ImageConvertType imageConvertType) {
        boolean result=true;
        if(manyImage){

        }else{
            result=this.image.convert(in, out, imageConvertType);
        }
        return result;
    }

    @Override
    public boolean convert(File in, OutputStream out, ImageConvertType imageConvertType) {
        boolean result=true;
        if(manyImage){

        }else{
            result=this.image.convert(in, out, imageConvertType);
        }
        return result;
    }

    @Override
    public boolean convertAll(OutputStream out, ImageConvertType fileConvertType, OptionResult optionResult) {
        boolean result=true;
        if(manyImage){
            //WordConvertType wordConvertType = WordConvertType.valueOf(fileConvertType.toString());
            result=this.file.convertAll(out, T.valueOf(clazz, fileConvertType.toString()),optionResult);
        }else{
            result=this.image.convertAll(out, fileConvertType, optionResult);
        }
        return result;
    }
}
