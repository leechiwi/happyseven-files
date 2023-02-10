package org.leechiwi.happyseven.files.text;

import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractText implements Text {
    public abstract boolean doPre(TextConvertType textConvertType);

    public abstract boolean doConvert(Object in, OutputStream out, TextConvertType textConvertType);
    @Override
    public boolean convert(InputStream in, OutputStream out, TextConvertType textConvertType) {
        if(doPre(textConvertType)){
            return doConvert(in, out, textConvertType);
        }
        return false;
    }

    @Override
    public boolean convert(File file, OutputStream out, TextConvertType textConvertType) {
        if(doPre(textConvertType)){
            return doConvert(file, out, textConvertType);
        }
        return false;
    }

    @Override
    public boolean convert(String path, OutputStream out, TextConvertType textConvertType) {
        if(doPre(textConvertType)){
            return doConvert(path, out, textConvertType);
        }
        return false;
    }

    @Override
    public boolean convertAll(OutputStream out, TextConvertType textConvertType) {
        if(doPre(textConvertType)){
            return doConvert(null, out, textConvertType);
        }
        return false;
    }
}
