package org.leechiwi.happyseven.files.cell;

import org.leechiwi.happyseven.files.cell.enums.CellConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Cell {
    boolean convert(InputStream in, OutputStream out, CellConvertType crllConvertType);

    boolean convert(File file, OutputStream out, CellConvertType crllConvertType);

    boolean convert(String path, OutputStream out, CellConvertType crllConvertType);

    boolean convertAll(OutputStream out, CellConvertType crllConvertType);
}
