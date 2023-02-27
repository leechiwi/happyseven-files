package org.leechiwi.happyseven.files.image.ap.proxy;

import com.aspose.words.Document;
import org.apache.commons.collections.CollectionUtils;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImagesDecorator;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.image.Image;
import org.leechiwi.happyseven.files.image.ap.ApImage;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ApImageProxy implements Image {
    private Doc<Document> apDoc;
    private Image image;
    private boolean manyImage;

    public ApImageProxy(ImageFormat sourceImageformat,List<Object> imagesIn,int width,int height) {
        if(CollectionUtils.isNotEmpty(imagesIn)){
            if(imagesIn.size()>1){
                this.manyImage=true;
                this.apDoc=new ApDocImagesDecorator(imagesIn,new ApDoc(null));
            }else{
                image=new ApImage(sourceImageformat,imagesIn.get(0),width,height);
            }
        }
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
            WordConvertType wordConvertType = WordConvertType.valueOf(fileConvertType.toString());
            result=this.apDoc.convertAll(out, wordConvertType,optionResult);
        }else{
            result=this.image.convertAll(out, fileConvertType, optionResult);
        }
        return result;
    }
}
