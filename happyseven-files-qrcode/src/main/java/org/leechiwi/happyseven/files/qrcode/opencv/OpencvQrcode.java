package org.leechiwi.happyseven.files.qrcode.opencv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.qrcode.Qrcode;
import org.leechiwi.happyseven.files.qrcode.config.QrcodeConfigs;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;
import org.opencv.wechat_qrcode.WeChatQRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OpencvQrcode implements Qrcode {
    private static QrcodeConfigs qrcodeConfigs;

    public OpencvQrcode(QrcodeConfigs qrcodeConfigs) {
        OpencvQrcode.qrcodeConfigs = qrcodeConfigs;
    }

    @Override
    public List<String> getQrcodeText(Object image) {
        List<Mat> points = new ArrayList<Mat>();
        List<String> list = null;
        try {
            list = decode(image, points);
            return list;
        } catch (Exception e) {
            log.error("opencv getQrcodeText error", e);
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

    private List<String> decode(Object file, List<Mat> points) {
        BufferedImage image = new ImageRead().loadImage(file);
        Mat img = Converters.img2mat(image);
        //微信二维码对象，要返回二维码坐标前2个参数必传；后2个在二维码小或不清晰时必传。
        WeChatQRCode we = new WeChatQRCode(
                qrcodeConfigs.getOpencvPath() + File.separator + QrcodeConfigs.getDetector_prototxt_path(),
                qrcodeConfigs.getOpencvPath() + File.separator + QrcodeConfigs.getDetector_caffe_model_path(),
                qrcodeConfigs.getOpencvPath() + File.separator + QrcodeConfigs.getSuper_resolution_prototxt_path(),
                qrcodeConfigs.getOpencvPath() + File.separator + QrcodeConfigs.getSuper_resolution_caffe_model_path()
        );
        //WeChatQRCode we = new WeChatQRCode();
        //List<Mat> points = new ArrayList<Mat>();
        //微信二维码引擎解码，返回的valList中存放的是解码后的数据，points中Mat存放的是二维码4个角的坐标
        List<String> list = we.detectAndDecode(img, points);
        return list;
    }

    @Override
    public boolean createQrcode(String text, OutputStream out) {
        return false;
    }
}
