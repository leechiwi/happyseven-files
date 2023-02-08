package org.leechiwi.happyseven.files.pdf.enums;

import java.util.EnumSet;
import java.util.Set;

public enum PdfConvertType {
    DOC(1, ".doc"),
    DOCX(6, ".docx"),
    XLSX(9, ".xlsx"),
    PPTX(14, ".pptx"),
    EPUB(10, ".epub"),
    HTML(3, ".html"),
    MOBI(8, ".mobi"),
    JPEG(1, ".jpg"),
    PNG(2, ".png"),
    GIF(3, ".gif"),
    BMP(4, ".bmp"),
    SVG(7, ".svg");
    private int code;
    private String ext;
    private PdfConvertType(int code, String ext) {
        this.code = code;
        this.ext = ext;
    }
    private static final Set<PdfConvertType> imageTypeSet = EnumSet.of(JPEG, PNG, GIF, BMP);

    public int getCode() {
        return this.code;
    }

    public String getExt() {
        return this.ext;
    }

    public static Set<PdfConvertType> getImageTypeSet(){
        return imageTypeSet;
    }
}
