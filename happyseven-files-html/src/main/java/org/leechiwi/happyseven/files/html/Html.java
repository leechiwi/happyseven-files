package org.leechiwi.happyseven.files.html;

import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Html<T> extends org.leechiwi.happyseven.files.base.File<HtmlConvertType> {

    boolean convert(InputStream in, OutputStream out, HtmlConvertType htmlConvertTypet);

    boolean convert(File file, OutputStream out, HtmlConvertType htmlConvertTypet);

    boolean convert(String path, OutputStream out, HtmlConvertType htmlConvertTypet);

    boolean convert(InputStream in, String out, HtmlConvertType htmlConvertTypet);

    boolean convert(File file, String out, HtmlConvertType htmlConvertTypet);

    boolean convert(String path, String out, HtmlConvertType htmlConvertTypet);

}
