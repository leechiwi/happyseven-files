package org.leechiwi.happyseven.files.text.enums;

import java.util.Set;

public enum TextConvertType {
    DOC(10, ".doc"),
    DOCX(20, ".docx"),
    PDF(40, ".pdf"),
    DOT(11, ".dot"),
    DOTX(22, ".dotx"),
    DOCM(21, ".docm"),
    DOTM(23, ".dotm"),
    HTML(50, ".html"),
    HTML_FIXED(45, ".html"),
    MHTML(51, ".mht"),
    SVG(44, ".svg"),
    JPEG(104, ".jpg"),
    PNG(101, ".png"),
    GIF(105, ".gif"),
    BMP(102, ".bmp"),
    TEXT(70, ".txt"),
    RTF(30, ".rtf"),
    ODT(60, ".odt"),
    EPUB(52, ".epub");
    private final int code;
    private final String ext;

    private TextConvertType(int code, String ext) {
        this.code = code;
        this.ext = ext;
    }

    public int getCode() {
        return this.code;
    }

    public String getExt() {
        return this.ext;
    }
}
