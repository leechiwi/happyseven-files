package org.leechiwi.happyseven.files.html.enums;

import com.aspose.html.rendering.image.ImageRenderingOptions;

import java.util.EnumSet;
import java.util.Set;

public enum HtmlConvertType {
    HTML(".html"),
    PDF(".pdf"),
    JPEG(".jpg"),
    PNG(".png"),
    BMP(".bmp"),
    GIF(".gif"),
    TIFF(".tif"),
    XPS(".xps"),
    MHTML(".mht"),
    MARKDOWN(".md");

    private String ext;

    private static final Set<HtmlConvertType> imageTypeSet = EnumSet.of(JPEG, PNG, GIF, BMP,TIFF);

    public static Set<HtmlConvertType> getImageTypeSet() {
        return imageTypeSet;
    }
    private HtmlConvertType(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return this.ext;
    }
}
