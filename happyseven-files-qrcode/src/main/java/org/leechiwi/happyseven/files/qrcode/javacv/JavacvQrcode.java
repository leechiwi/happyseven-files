package org.leechiwi.happyseven.files.qrcode.javacv;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_wechat_qrcode.WeChatQRCode;
import org.leechiwi.happyseven.files.base.read.ImageRead;
import org.leechiwi.happyseven.files.qrcode.Qrcode;
import org.leechiwi.happyseven.files.qrcode.config.QrcodeConfigs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JavacvQrcode implements Qrcode {
    private QrcodeConfigs qrcodeConfigs;
    public JavacvQrcode(QrcodeConfigs qrcodeConfigs){
        this.qrcodeConfigs=qrcodeConfigs;
    }
    @Override
    public List<String> getQrcodeText(Object image) {
        List<String> list=new ArrayList<>();
        MatVector points=new MatVector();
        StringVector stringVector = decode(image, points);
        if(Objects.isNull(stringVector)||stringVector.size()==0){
            return list;
        }
        for(long i=0,size= stringVector.size();i<size;i++){
            BytePointer bytePointer = stringVector.get(i);//取识别结果中的第一个二维码
            list.add(bytePointer.getString());
        }
        return list;
    }

    @Override
    public String getSingleQrcodeText(Object image) {
        List<String> list = getQrcodeText(image);
        if (CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return list.get(0);
    }
    private StringVector decode(Object file, MatVector points){
        BufferedImage image = new ImageRead().loadImage(file);
        Mat img = Java2DFrameUtils.toMat(image);
        //微信二维码对象，要返回二维码坐标前2个参数必传；后2个在二维码小或不清晰时必传。
        //WeChatQRCode we = new WeChatQRCode(basePath+File.separator+ Configs.detector_prototxt_path, basePath+File.separator+ Configs.detector_caffe_model_path, basePath+File.separator+ Configs.super_resolution_prototxt_path, basePath+File.separator+ Configs.super_resolution_caffe_model_path);
        WeChatQRCode we=new WeChatQRCode(qrcodeConfigs.getOpencvPath()+ File.separator+ QrcodeConfigs.getDetector_prototxt_path(),
                qrcodeConfigs.getOpencvPath()+File.separator+ QrcodeConfigs.getDetector_caffe_model_path(),
                qrcodeConfigs.getOpencvPath()+File.separator+ QrcodeConfigs.getSuper_resolution_prototxt_path(),
                qrcodeConfigs.getOpencvPath()+File.separator+ QrcodeConfigs.getSuper_resolution_caffe_model_path());
        //QRCodeDetector qrCodeDetector = new QRCodeDetector();
        //MatVector points=new MatVector();
        //StringVector decoded_info = new StringVector();
        //微信二维码引擎解码，返回的valList中存放的是解码后的数据，points中Mat存放的是二维码4个角的坐标
        StringVector stringVector = we.detectAndDecode(img, points);
        //boolean success = qrCodeDetector.detectAndDecodeMulti(img, decoded_info);
        //BytePointer bytePointer = stringVector.get(0);//取识别结果中的第一个二维码
        //result=bytePointer.getString();
        return stringVector;
    }
    @Override
    public boolean createQrcode(String text, OutputStream out) {
        return false;
    }
}
