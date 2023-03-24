package org.leechiwi.happyseven.files.image.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.image.Image;
import org.leechiwi.happyseven.files.image.ap.factory.ApImageConvertTypeFactory;
import org.leechiwi.happyseven.files.image.ap.factory.ImageSourceFactory;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

import java.io.*;
import java.util.Objects;

@Slf4j
public class ApImage extends ImageLicense implements Image {
    private ImageFormat sourceImageformat;
    private com.aspose.imaging.Image image;
    private float width;
    private float height;
    private int pages;//gif的第几帧
    public ApImage(ImageFormat sourceImageformat){
        this(sourceImageformat,null,0,0);
    }

    public ApImage(ImageFormat sourceImageformat, Object in,float width,float height,int pages) {
        this.sourceImageformat = sourceImageformat;
        if (Objects.nonNull(in)) {
            this.image = createImage(sourceImageformat, in);
        }
        this.width=width;
        this.height=height;
        this.pages=pages;
    }

    public ApImage(ImageFormat sourceImageformat, Object in,float width,float height){
        this(sourceImageformat,in,width,height,0);
    }

    public ApImage(ImageFormat sourceImageformat, Object in){
        this(sourceImageformat,in,0,0);
    }
    private com.aspose.imaging.Image createImage(ImageFormat sourceImageformat, Object in) {
        com.aspose.imaging.Image image = null;
        try {
            InputStream inputStream = new FileRead().loadFile(in);
            image = ImageSourceFactory.createImageSource(sourceImageformat, inputStream);
        } catch (Exception e) {
            throw new HappysevenException("create aspose image Document error", e);
        }
        return image;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, ImageConvertType imageConvertType) {
        try {
            com.aspose.imaging.Image image = createImage(sourceImageformat, in);
            image.save(out, new ApImageConvertTypeFactory(this.width,this.height,this.pages,this.image).convertImageConvertType(imageConvertType));
            image.close();
        } catch (HappysevenException e) {
            log.error("aspose image convert stream error");
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(String in, OutputStream out, ImageConvertType imageConvertType) {
        try {
            com.aspose.imaging.Image image =createImage(sourceImageformat, in);
            image.save(out, new ApImageConvertTypeFactory(this.width,this.height,this.pages,this.image).convertImageConvertType(imageConvertType));
            image.close();
        } catch (HappysevenException e) {
            log.error("aspose image convert path error");
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(File in, OutputStream out, ImageConvertType imageConvertType) {
        InputStream input = null;
        try {
            input = new FileInputStream(in);
        } catch (FileNotFoundException e) {
            log.error("aspose image convert file error");
            return false;
        }
        return this.convert(input, out, imageConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, ImageConvertType imageConvertType, OptionResult optionResult) {
        try {
            this.image.save(out,new ApImageConvertTypeFactory(this.width,this.height,this.pages,this.image).convertImageConvertType(imageConvertType));
            image.close();
        } catch (Exception e) {
            log.error("aspose image convert all error",e);
            return false;
        }
        return true;
    }
}
