package org.leechiwi.happyseven.files.doc;

import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

public interface WordConvertTypeFactory<T> {

    T convertWordConvertType(WordConvertType wordConvertType);

}
