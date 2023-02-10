package org.leechiwi.happyseven.files.slide.enums;

import java.util.EnumSet;
import java.util.Set;

public enum SlideConvertType {
    Ppt(0,".ppt"),
    Pdf(1,".pdf"),
    Xps(2,".xps"),
    Pptx(3,".pptx"),
    Ppsx(4,".ppsx"),
    Tiff(5,".tif"),
    Odp(6,".odp"),
    Pptm(7,".pptm"),
    Ppsm(9,".ppsm"),
    Potx(10,".potx"),
    Potm(11,".potm"),
    Html(13,".html"),
    Swf(15,".swf"),
    Otp(17,".otp"),
    Pps(19,".pps"),
    Pot(20,".pot"),
    Fodp(21,".fodp"),
    JPEG(104, ".jpg"),
    PNG(101, ".png"),
    GIF(105, ".gif"),
    BMP(102, ".bmp");
    private final int code;
    private final String ext;

    private static final Set<SlideConvertType> imageTypeSet = EnumSet.of(JPEG, PNG, GIF, BMP);
    private SlideConvertType(int code, String ext) {
        this.code = code;
        this.ext = ext;
    }

    public static Set<SlideConvertType> getImageTypeSet() {
        return imageTypeSet;
    }

    public int getCode() {
        return this.code;
    }

    public String getExt() {
        return this.ext;
    }
}
