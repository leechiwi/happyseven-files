package org.leechiwi.happyseven.files.barcode.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.zxing.factory.WriterFactory;
import org.leechiwi.happyseven.files.barcode.zxing.factory.ZXingBarcodeTypeFactory;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Slf4j
public class ZXingBarcode implements Barcode {
    private BarcodeType barcodeType;
    private Writer writer;
    private int width;
    private int height;

    public ZXingBarcode(BarcodeType barcodeType, int width, int height) {
        this.barcodeType = barcodeType;
        this.writer = new WriterFactory().createWriter(barcodeType);
        this.width = width;
        this.height = height;
    }

    public ZXingBarcode(BarcodeType barcodeType) {
        this(barcodeType,195,40);
    }

    @Override
    public List<String> getBarcodeText(Object image) {
        List<String> result = new ArrayList<>();
        result.add(recognizeBarcode(image));
        return result;
    }

    private String recognizeBarcode(Object image) {
        try {
            BufferedImage bufferedImage = new ImageRead().loadImage(image);
            LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(luminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            //字符编码类型
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            //优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            //复杂模式
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            Result resultObject = new MultiFormatReader().decode(binaryBitmap, hints);
            return resultObject.getText();
        } catch (Exception e) {
            log.error("ZXing getBarcodeText Error", e);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String getSingleBarcodeText(Object image) {
        List<String> barcodeTextList = getBarcodeText(image);
        if (CollectionUtils.isEmpty(barcodeTextList)) {
            return StringUtils.EMPTY;
        }
        return barcodeTextList.get(0);
    }

    @Override
    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat) {
        BufferedImage result = null;
        try {
            BarcodeFormat barcodeFormat = new ZXingBarcodeTypeFactory().convertBarcode(this.barcodeType);
            BitMatrix bitMatrix = writer.encode(text, barcodeFormat, this.width, this.height, null);
            result = MatrixToImageWriter.toBufferedImage(bitMatrix);
            if (Objects.nonNull(out)) {
                MatrixToImageWriter.writeToStream(bitMatrix, imageFormat.getName(), out);
            }
        } catch (WriterException e) {
            log.error("ZXing CreateBarcode error",e);
        } catch (IOException e) {
            log.error("ZXing CreateBarcode error",e);
        } catch (NullPointerException e){
            log.error("ZXing CreateBarcode error(unsupport)",e);
        }
        return result;
    }

    @Override
    public void CreateBarcode(String text, OutputStream out) {
        CreateBarcode(text, out, null, ImageFormat.JPEG);
    }
}
