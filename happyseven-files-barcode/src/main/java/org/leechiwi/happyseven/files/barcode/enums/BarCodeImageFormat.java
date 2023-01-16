package org.leechiwi.happyseven.files.barcode.enums;

public enum BarCodeImageFormat {
    BMP(0,"bmp"),
    GIF(1,"gif"),
    JPEG(2,"jpg"),
    PNG(3,"png"),
    TIFF(4,"tif"),
    TIFF_IN_CMYK(5,"tiff_in_cmyk"),
    EMF(6,"emf"),
    SVG(7,"svg");
    private final int a;
    private final String name;

    private BarCodeImageFormat(int value,String name) {
        this.a = value;
        this.name=name;
    }

    public int getValue() {
        return this.a;
    }

    public String getName() {
        return name;
    }
}
