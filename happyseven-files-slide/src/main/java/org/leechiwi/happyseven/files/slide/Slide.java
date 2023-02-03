package org.leechiwi.happyseven.files.slide;

import org.leechiwi.happyseven.files.slide.enums.SlideConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Slide {
    boolean convert(InputStream in, OutputStream out, SlideConvertType slideConvertType);

    boolean convert(File file, OutputStream out, SlideConvertType slideConvertType);

    boolean convert(String path, OutputStream out, SlideConvertType slideConvertType);

    boolean convertAll(OutputStream out, SlideConvertType slideConvertType);
}
