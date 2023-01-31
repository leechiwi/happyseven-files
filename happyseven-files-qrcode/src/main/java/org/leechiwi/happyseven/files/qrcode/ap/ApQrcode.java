package org.leechiwi.happyseven.files.qrcode.ap;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.QRVersion;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.qrcode.Qrcode;
import org.leechiwi.happyseven.files.qrcode.ap.factory.ApQrcodeTypeFactory;
import org.leechiwi.happyseven.files.qrcode.enums.QrcodeType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
public class ApQrcode implements Qrcode {
    private QrcodeType qrcodeType;

    public ApQrcode(QrcodeType qrcodeType) {
        this.qrcodeType = qrcodeType;
    }
    public ApQrcode() {
        this.qrcodeType = QrcodeType.FORCE_MICRO_QR;
    }
    @Override
    public List<String> getQrcodeText(Object image) {
        List<String> list = new ArrayList<>();
        BarCodeResult[] codeResults = getQrCode(image);
        if(Objects.isNull(codeResults)||codeResults.length==0){
            return list;
        }
        for (BarCodeResult barCodeResult : codeResults) {
            list.add(barCodeResult.getCodeText());
        }
        return list;
    }

    @Override
    public String getSingleQrcodeText(Object image) {
        List<String> list = getQrcodeText(image);
        if(CollectionUtils.isEmpty(list)){
            return StringUtils.EMPTY;
        }
        return list.get(0);
    }
    private  BarCodeResult[] getQrCode(Object image){
        BarCodeResult[] result=null;
        BufferedImage bufferedImage = new ImageRead().loadImage(image);
        if(Objects.isNull(bufferedImage)){
            return result;
        }
        BarCodeReader reader = new BarCodeReader(bufferedImage, DecodeType.QR);
        result = reader.readBarCodes();
        return result;
    }
    private BufferedImage create(String text, OutputStream out){
        // Initialize an instance of BarcodeGenerator class
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, text);
        gen.getParameters().getBarcode().getXDimension().setPixels(4);
        gen.getParameters().setResolution(400);
        // Set Auto version
        gen.getParameters().getBarcode().getQR().setQrVersion(QRVersion.AUTO);
        //Set ForceMicroQR QR encode type
        gen.getParameters().getBarcode().getQR().setQrEncodeType(new ApQrcodeTypeFactory().convertBarcode(qrcodeType));
        BufferedImage bufferedImage = gen.generateBarCodeImage();
        if(Objects.nonNull(out)){
            try {
                gen.save(out, BarCodeImageFormat.PNG);
            } catch (IOException e) {
               log.error("apqrcode create outputstream error",e);
            }
        }
        return bufferedImage;
    }
    @Override
    public boolean createQrcode(String text, OutputStream out) {
        BufferedImage bufferedImage = create(text, out);
        if(Objects.nonNull(bufferedImage)){
            return true;
        }
        return false;
    }
}
