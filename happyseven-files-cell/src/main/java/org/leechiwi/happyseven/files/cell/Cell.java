package org.leechiwi.happyseven.files.cell;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.cell.enums.CellConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Cell<T> extends org.leechiwi.happyseven.files.base.File<CellConvertType> {
    boolean convert(InputStream in, OutputStream out, CellConvertType cellConvertType);

    boolean convert(File file, OutputStream out, CellConvertType crllConvertType);

    boolean convert(String path, OutputStream out, CellConvertType crllConvertType);

    T getWorkbook();
}
