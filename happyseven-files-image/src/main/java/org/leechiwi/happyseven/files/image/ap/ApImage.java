package org.leechiwi.happyseven.files.image.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
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

    public ApImage(ImageFormat sourceImageformat){
        this.sourceImageformat = sourceImageformat;
    }
    public ApImage(ImageFormat sourceImageformat, Object in) {
        this.sourceImageformat = sourceImageformat;
        if (Objects.nonNull(in)) {
            this.image = createImage(sourceImageformat, in);
        }
    }

    private com.aspose.imaging.Image createImage(ImageFormat sourceImageformat, Object in) {
        com.aspose.imaging.Image image = null;
        try {
            InputStream inputStream = null;
            if (in instanceof InputStream) {
                inputStream = (InputStream) in;
            } else if (in instanceof File) {
                inputStream = new FileInputStream((File)in);
            } else if (in instanceof String) {
                inputStream = new FileInputStream(new File((String) in));
            }
            image = ImageSourceFactory.createImageSource(sourceImageformat, inputStream);
        } catch (Exception e) {
            throw new HappysevenException("create aspose word Document error", e);
        }
        return image;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, ImageConvertType imageConvertType) {
        try {
            com.aspose.imaging.Image image = ImageSourceFactory.createImageSource(sourceImageformat, in);
            image.save(out, new ApImageConvertTypeFactory().convertImageConvertType(imageConvertType));
        } catch (HappysevenException e) {
            log.error("aspose image convert stream error");
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(String in, OutputStream out, ImageConvertType imageConvertType) {
        try {
            com.aspose.imaging.Image image = ImageSourceFactory.createImageSource(sourceImageformat, in);
            image.save(out, new ApImageConvertTypeFactory().convertImageConvertType(imageConvertType));
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
    public boolean convertAll(OutputStream out, ImageConvertType imageConvertType) {
        try {
            this.image.save(out,new ApImageConvertTypeFactory().convertImageConvertType(imageConvertType));
        } catch (Exception e) {
            log.error("aspose image convert all error",e);
            return false;
        }
        return true;
    }
}
