package org.leechiwi.happyseven.files.ofd;

import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;

import java.io.OutputStream;

public interface OfdConvertTypeFactory<T> {
    T convertOfdConvertType(OfdConvertType ofdConvertType);
}
