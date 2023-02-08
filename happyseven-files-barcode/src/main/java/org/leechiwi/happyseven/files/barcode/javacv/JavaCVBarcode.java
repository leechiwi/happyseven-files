package org.leechiwi.happyseven.files.barcode.javacv;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_barcode.BarcodeDetector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.leechiwi.happyseven.files.barcode.AbstractBarcode;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.javacv.entity.DecodeResult;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.*;

public class JavaCVBarcode extends AbstractBarcode {
    private BarcodeType barcodeType;
    private static final Map<BarcodeType,Boolean> supportTypeMap;
    static{
        supportTypeMap=new HashMap<BarcodeType,Boolean>(){{
            put(BarcodeType.NONE,true);
            put(BarcodeType.EAN_8,true);
            put(BarcodeType.EAN_13,true);
            put(BarcodeType.UPCA,true);
            put(BarcodeType.UPCE,true);
            put(BarcodeType.UPC_EAN_EXTENSION,true);
        }};
    }
    public JavaCVBarcode(BarcodeType barcodeType) {
        this.barcodeType = barcodeType;
    }
    @Override
    public boolean doPre() {
        return supportTypeMap.containsKey(this.barcodeType);
    }

    @Override
    public List<String> doBarcodeText(Object image) {
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
    public String doSingleBarcodeText(Object image) {
        String result= StringUtils.EMPTY;
        List<String> barcodeText = doBarcodeText(image);
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
}
