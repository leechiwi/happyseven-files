package org.leechiwi.happyseven.files.doc;

import com.aspose.words.Document;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Doc {
    boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertTypet);

    boolean convert(File file, OutputStream out, WordConvertType wordConvertType);

    boolean convert(String path, OutputStream out, WordConvertType wordConvertType);

    boolean convertAll(OutputStream out, WordConvertType wordConvertType, OptionResult optionResult);

    Document createTextDoc(String text);

}
