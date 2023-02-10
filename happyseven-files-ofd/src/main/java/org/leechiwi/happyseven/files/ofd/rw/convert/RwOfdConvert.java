package org.leechiwi.happyseven.files.ofd.rw.convert;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.ofdrw.reader.OFDReader;

import java.io.InputStream;
import java.io.OutputStream;

public interface RwOfdConvert {
    boolean convert(Object in, OutputStream out, OptionResult optionResult);
}
