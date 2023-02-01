package org.leechiwi.happyseven.files.barcode.javacv;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_barcode.BarcodeDetector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.javacv.entity.DecodeResult;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JavaCVBarcode implements Barcode {
    @Override
    public List<String> getBarcodeText(Object image) {
        List<String> result=null;
        DecodeResult decodeResult = decode(image);
        if(Objects.isNull(decodeResult)){
            return result;
        }
        StringVector decoded_info=decodeResult.getDecoded_info();
        result = new ArrayList<>();
        for(long i=0,size=decoded_info.size();i<size;i++){
            result.add(decoded_info.get(i).getString());
        }
        return result;
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        String result= StringUtils.EMPTY;
        List<String> barcodeText = getBarcodeText(image);
        if(CollectionUtils.isNotEmpty(barcodeText)){
            result=barcodeText.get(0);
        }
        return result;
    }
    private DecodeResult decode(Object file) {
        DecodeResult decodeResult=null;
        //String result= StringUtils.EMPTY;
        BufferedImage image  = new ImageRead().loadImage(file);
        Mat img = Java2DFrameUtils.toMat(image);
        StringVector decoded_info = new StringVector();
        IntPointer type = new IntPointer();
        BarcodeDetector bardecode = new BarcodeDetector();
        Mat mat = new Mat();
        boolean result = bardecode.detectAndDecode(img, decoded_info, type, mat);
        if(result) {
            decodeResult = new DecodeResult(decoded_info, type, mat);
        }
        return decodeResult;
    }
    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        return null;
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {

    }
}
