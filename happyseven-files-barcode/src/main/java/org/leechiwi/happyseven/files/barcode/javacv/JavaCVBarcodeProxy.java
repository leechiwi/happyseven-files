package org.leechiwi.happyseven.files.barcode.javacv;

import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaCVBarcodeProxy implements Barcode {
    private BarcodeType barcodeType;
    private JavaCVBarcode javaCVBarcode=new JavaCVBarcode();
    private static final Map<BarcodeType,Boolean> supportTypeMap;
    public JavaCVBarcodeProxy(BarcodeType barcodeType) {
        this.barcodeType = barcodeType;
    }
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
    @Override
    public List<String> getBarcodeText(Object image) {
        if(checkSupportType()){
            return  javaCVBarcode.getBarcodeText(image);
        }
        return null;
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        if(checkSupportType()){
            return javaCVBarcode.getSingleBarcodeText(image);
        }
        return StringUtils.EMPTY;
    }
    private boolean checkSupportType(){
        return supportTypeMap.containsKey(this.barcodeType);
    }
    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        return null;
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {

    }
}
