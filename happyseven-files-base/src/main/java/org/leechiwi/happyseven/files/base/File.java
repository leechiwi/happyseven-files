package org.leechiwi.happyseven.files.base;

import org.leechiwi.happyseven.files.base.entity.OptionResult;

import java.io.OutputStream;

public interface File<T> {
    boolean convertAll(OutputStream out, T fileConvertType, OptionResult optionResult);
}
