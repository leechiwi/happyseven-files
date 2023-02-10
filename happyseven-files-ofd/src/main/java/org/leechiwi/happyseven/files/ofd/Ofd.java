package org.leechiwi.happyseven.files.ofd;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Ofd {

    boolean convertAll(Object in, OutputStream out, OfdConvertType ofdConvertType, OptionResult optionResult);
}
