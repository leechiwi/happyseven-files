package org.leechiwi.happyseven.files.barcode.ap;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApImageFormatFactory;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApBarcodeClassificationsFactory;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.ap.encodetype.CustomEncodeType;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApBarcodeTypeFactory;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
public class ApBarcode implements Barcode {
    private BarcodeType barcodeType;
    public ApBarcode(BarcodeType barcodeType){
        this.barcodeType=barcodeType;
    }
    @Override
    public List<String> getBarcodeText(Object image) {
        List<String> result = new ArrayList<>();
        ApBarcodeTypeFactory factory=new ApBarcodeTypeFactory();
        SingleDecodeType singleDecodeType = factory.convertBarcode(this.barcodeType);
        BarCodeResult[] barCodeResults = getBarCode(image, singleDecodeType);
        if(Objects.isNull(barCodeResults)||barCodeResults.length==0){
            return result;
        }
        for (BarCodeResult barCodeResult : barCodeResults) {
            result.add(barCodeResult.getCodeText());
        }
        return result;
    }
    public String getSingleBarcodeText(Object image){
        List<String> barcodeTextList = getBarcodeText(image);
        if(CollectionUtils.isEmpty(barcodeTextList)){
            return StringUtils.EMPTY;
        }
        return barcodeTextList.get(0);
    }
    private  BarCodeResult[] getBarCode(Object image, SingleDecodeType codeType){
        BarCodeResult[] result=null;
        BufferedImage bufferedImage = new ImageRead().loadImage(image);
        if(Objects.isNull(bufferedImage)){
            return result;
        }
        BarCodeReader reader = new BarCodeReader(bufferedImage,codeType);
        result = reader.readBarCodes();
        return result;
    }
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat){
        BufferedImage bufferedImage=null;
        SingleDecodeType singleDecodeType = new ApBarcodeTypeFactory().convertBarcode(this.barcodeType);
        BaseEncodeType baseEncodeType=new CustomEncodeType(singleDecodeType.getTypeIndex(),singleDecodeType.getTypeName(),new ApBarcodeClassificationsFactory().convertBarcodeClassifications(barcodeClassifications));
        BarcodeGenerator barcodeGenerator=new BarcodeGenerator(baseEncodeType, text);
        try {
            bufferedImage = barcodeGenerator.generateBarCodeImage();
            if(Objects.nonNull(out)) {
                com.aspose.barcode.generation.BarCodeImageFormat apBarCodeImageFormat = new ApImageFormatFactory().convertBarCodeImageFormat(imageFormat);
                barcodeGenerator.save(out, apBarCodeImageFormat);
            }
        } catch (IOException e) {
            log.error("CreateBarcode error",e);
        }
        return bufferedImage;
    }
    public void CreateBarcode(String text,OutputStream out){
        CreateBarcode(text,out, BarcodeClassifications.NONE, ImageFormat.JPEG);
    }
}
