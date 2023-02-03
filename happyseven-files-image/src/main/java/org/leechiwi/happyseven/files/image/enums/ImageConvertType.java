package org.leechiwi.happyseven.files.image.enums;

import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.fileformats.pdf.PdfDocumentInfo;
import com.aspose.imaging.imageoptions.*;

public enum ImageConvertType {
    JPEG(".jpg"),
    PNG(".png"),
    GIF(".gif"),
    BMP(".bmp"),
    TIFF(".tif"),
    PDF(".pdf");
    private String ext;

    private ImageConvertType(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return this.ext;
    }
}
