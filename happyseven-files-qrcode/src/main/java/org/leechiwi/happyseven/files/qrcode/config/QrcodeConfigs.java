package org.leechiwi.happyseven.files.qrcode.config;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Objects;

public class QrcodeConfigs {
    private static QrcodeConfigs qrcodeConfigs;
    private static final String detector_prototxt_path = "detect.prototxt";
    private static final String detector_caffe_model_path = "detect.caffemodel";
    private static final String super_resolution_prototxt_path = "sr.prototxt";
    private static final String super_resolution_caffe_model_path = "sr.caffemodel";
    private static String lib_path;
    private static String opencvPath;

    static {
        String lib = "";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            lib = "opencv_java452.dll";
        } else if (os.contains("linux")) {
            lib = "libopencv_java452.so";
        } else if (os.contains("mac")) {
            lib = "opencv_java452.jnilib";
        }
        lib_path = lib;
    }

    private QrcodeConfigs() {

    }

    public static QrcodeConfigs build(String path,boolean loadLibrary) {
        if (Objects.isNull(qrcodeConfigs)) {
            synchronized (QrcodeConfigs.class) {
                if (Objects.isNull(qrcodeConfigs)) {
                    qrcodeConfigs = new QrcodeConfigs();
                    qrcodeConfigs.setOpencvPath(path);
                    if(loadLibrary) {
                        System.load(qrcodeConfigs.getOpencvPath() + File.separator + lib_path);
                    }
                }
            }
        }
        return qrcodeConfigs;
    }

    private void setOpencvPath(String opencvPath) {
        QrcodeConfigs.opencvPath = opencvPath;
    }

    public String getOpencvPath() {
        return opencvPath;
    }

    public static String getDetector_prototxt_path() {
        return detector_prototxt_path;
    }

    public static String getDetector_caffe_model_path() {
        return detector_caffe_model_path;
    }

    public static String getSuper_resolution_prototxt_path() {
        return super_resolution_prototxt_path;
    }

    public static String getSuper_resolution_caffe_model_path() {
        return super_resolution_caffe_model_path;
    }

    public static String getLib_path() {
        return lib_path;
    }
}
