package org.leechiwi.happyseven.files.qrcode.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.qrcode.Qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Slf4j
public class ZXingQrcode implements Qrcode {
    private static final int defaultWidth=100;
    private static final int defaultHeight=100;
    private static final String defaultFormat="png";
    private int width;
    private int height;
    private String format;

    public ZXingQrcode(int width, int height, String format) {
        this.width = width;
        this.height = height;
        this.format = format;
    }

    public ZXingQrcode(int width, int height) {
        this(width,height,defaultFormat);
    }

    public ZXingQrcode(String format) {
        this(defaultWidth,defaultHeight,format);
    }

    public ZXingQrcode() {
        this(defaultWidth,defaultHeight,defaultFormat);
    }
    private Result decode(Object file){
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        ImageRead imageRead = new ImageRead();
        BufferedImage image = imageRead.loadImage(file);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // 定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        Result result = null;
        try {
            result = multiFormatReader.decode(binaryBitmap, hints);
        } catch (NotFoundException e) {
            log.error("zxing decode qrcode error",e);
        }
        return result;
    }
    @Override
    public List<String> getQrcodeText(Object image) {
        List<String> list=new ArrayList<>();
        Result result = decode(image);
        if(Objects.nonNull(result)){
            list.add(result.getText());
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

    private BufferedImage create(String content,OutputStream out) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 设定内容编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);// 设定纠错级别
        hints.put(EncodeHintType.MARGIN, 0);// 设定图片周围留白，0表示不留
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, this.width, this.height, hints);
        } catch (WriterException e) {
            log.error("create qrcode error",e);
            return null;
        }
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        if(Objects.nonNull(out)){
            try {
                MatrixToImageWriter.writeToStream(bitMatrix,this.format,out);
            } catch (IOException e) {
                log.error("create qrcode outputstream error",e);
                return null;
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
