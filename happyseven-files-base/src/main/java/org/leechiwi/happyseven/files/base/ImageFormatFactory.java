package org.leechiwi.happyseven.files.base;

import org.leechiwi.happyseven.files.base.enums.ImageFormat;

public interface ImageFormatFactory<T> {
    T convertBarCodeImageFormat(ImageFormat imageFormat);
}