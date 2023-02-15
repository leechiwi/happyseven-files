package org.leechiwi.happyseven.files.image;

import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Image extends org.leechiwi.happyseven.files.base.File<ImageConvertType> {
    boolean convert(InputStream in,OutputStream out, ImageConvertType imageConvertType);

    boolean convert(String in, OutputStream out, ImageConvertType imageConvertType);

    boolean convert(File in, OutputStream out, ImageConvertType imageConvertType);

}
