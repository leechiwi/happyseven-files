package org.leechiwi.happyseven.files.barcode.javacv.entity;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.StringVector;

public class DecodeResult {
    private StringVector decoded_info;
    private IntPointer type;
    private Mat mat;

    public DecodeResult(StringVector decoded_info, IntPointer type, Mat mat) {
        this.decoded_info = decoded_info;
        this.type = type;
        this.mat = mat;
    }

    public StringVector getDecoded_info() {
        return decoded_info;
    }

    public void setDecoded_info(StringVector decoded_info) {
        this.decoded_info = decoded_info;
    }

    public IntPointer getType() {
        return type;
    }

    public void setType(IntPointer type) {
        this.type = type;
    }

    public Mat getMat() {
        return mat;
    }

    public void setMat(Mat mat) {
        this.mat = mat;
    }
}
