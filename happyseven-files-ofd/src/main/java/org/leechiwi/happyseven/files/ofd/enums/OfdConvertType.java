package org.leechiwi.happyseven.files.ofd.enums;

public enum OfdConvertType {
    PDF(1,".pdf"),
    PNG(2,".png"),
    HTML(3,".html"),
    SVG(4,".svg");
    private int code;
    private String ext;

    OfdConvertType(int code, String ext) {
        this.code = code;
        this.ext = ext;
    }

    public int getCode() {
        return code;
    }

    public String getExt() {
        return ext;
    }
}
