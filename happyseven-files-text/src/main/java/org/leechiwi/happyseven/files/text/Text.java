package org.leechiwi.happyseven.files.text;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Text {
    boolean convert(InputStream in, OutputStream out, TextConvertType textConvertType);

    boolean convert(File file, OutputStream out, TextConvertType textConvertType);

    boolean convert(String path, OutputStream out, TextConvertType textConvertType);

    boolean convertAll(OutputStream out, TextConvertType textConvertType, OptionResult optionResult);
}
