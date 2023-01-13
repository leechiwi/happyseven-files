package org.leechiwi.happyseven.files.barcode.ap;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApBarcode implements Barcode {
    @Override
    public List<String> getBarcodeText(Object image, SingleDecodeType codeType) {
        List<String> result = new ArrayList<>();
        BarCodeResult[] barCodeResults = getBarCode(image, codeType);
        if(Objects.isNull(barCodeResults)||barCodeResults.length==0){
            return result;
        }
        for (BarCodeResult barCodeResult : barCodeResults) {
            result.add(barCodeResult.getCodeText());
        }
        return result;
    }
    public String getSingleBarcodeText(Object image, SingleDecodeType codeType){
        List<String> barcodeTextList = getBarcodeText(image, codeType);
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
}
