package org.leechiwi.happyseven.files.image.ap.factory;

import com.aspose.imaging.Image;
import com.aspose.imaging.fileformats.bmp.BmpImage;
import com.aspose.imaging.fileformats.gif.GifImage;
import com.aspose.imaging.fileformats.jpeg.JpegImage;
import com.aspose.imaging.fileformats.png.PngImage;
import com.aspose.imaging.fileformats.tiff.TiffImage;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;

import java.io.InputStream;

public class ImageSourceFactory {
    public static Image createImageSource(ImageFormat imageFormat, InputStream in) throws HappysevenException{
        if(ImageFormat.JPEG==imageFormat){
            return JpegImage.load(in);
        }else if(ImageFormat.PNG==imageFormat){
            return PngImage.load(in);
        }else if(ImageFormat.BMP==imageFormat){
            return BmpImage.load(in);
        }else if(ImageFormat.GIF==imageFormat){
            return GifImage.load(in);
        }else if(ImageFormat.TIFF==imageFormat){
            return TiffImage.load(in);
        }
        throw new HappysevenException("ImageSourceFactory not support such Source of image");
    }
    public static Image createImageSource(ImageFormat imageFormat, String in) throws HappysevenException{
        if(ImageFormat.JPEG==imageFormat){
            return JpegImage.load(in);
        }else if(ImageFormat.PNG==imageFormat){
            return PngImage.load(in);
        }else if(ImageFormat.BMP==imageFormat){
            return BmpImage.load(in);
        }else if(ImageFormat.GIF==imageFormat){
            return GifImage.load(in);
        }else if(ImageFormat.TIFF==imageFormat){
            return TiffImage.load(in);
        }
        throw new HappysevenException("ImageSourceFactory not support such Source of image");
    }
}
