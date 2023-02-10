package org.leechiwi.happyseven.files.cell.enums;

import java.util.EnumSet;
import java.util.Set;

public enum CellConvertType {
    CSV(1, ".csv"),
    XLSX(6, ".xlsx"),
    XLSM(7, ".xlsm"),
    XLTX(8, ".xltx"),
    XLTM(9,".xltm"),
    XLAM(10,".xlam"),
    TSV(11,".tsv"),
    TAB_DELIMITED(11,".tsv"),
    HTML(12,".html"),
    M_HTML(17,".html"),
    ODS(14,".ods"),
    EXCEL_97_TO_2003(5,".xls"),
    SPREADSHEET_ML(15,".csv"),
    XLSB(16,".xlsb"),
    AUTO(0,".csv"),
    UNKNOWN(255,"unknown"),
    PDF(13,".pdf"),
    XPS(20,".xps"),
    TIFF(21,".tif"),
    SVG(22,".scg"),
    DIF(30,".dif"),
    NUMBERS(56,".numbers"),
    MARKDOWN(57,".md"),
    FODS(59,".fods"),
    SXC(60,".sxc"),
    PPTX(61,".pptx"),
    DOCX(62,".docx"),
    JPEG(104, ".jpg"),
    PNG(101, ".png"),
    GIF(105, ".gif"),
    BMP(102, ".bmp");
    private final int code;
    private final String ext;

    private static final Set<CellConvertType> imageTypeSet = EnumSet.of(JPEG, PNG, GIF, BMP);
    private CellConvertType(int code, String ext) {
        this.code = code;
        this.ext = ext;
    }

    public static Set<CellConvertType> getImageTypeSet() {
        return imageTypeSet;
    }

    public int getCode() {
        return this.code;
    }

    public String getExt() {
        return this.ext;
    }
}
