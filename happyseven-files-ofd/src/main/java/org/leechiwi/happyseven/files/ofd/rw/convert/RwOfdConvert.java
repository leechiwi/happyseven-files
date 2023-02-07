package org.leechiwi.happyseven.files.ofd.rw.convert;

import org.ofdrw.reader.OFDReader;

import java.io.InputStream;
import java.io.OutputStream;

public interface RwOfdConvert {
    boolean convert(Object in, OutputStream out);
}
