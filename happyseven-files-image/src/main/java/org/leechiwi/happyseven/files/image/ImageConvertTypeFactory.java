package org.leechiwi.happyseven.files.image;

import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

public interface ImageConvertTypeFactory <T>{
    T convertImageConvertType(ImageConvertType imageConvertType);
}
