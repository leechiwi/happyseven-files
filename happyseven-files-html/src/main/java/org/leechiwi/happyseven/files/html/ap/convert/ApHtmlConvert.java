package org.leechiwi.happyseven.files.html.ap.convert;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;

public interface ApHtmlConvert {
    boolean convert(Object in, String out, HtmlConvertType htmlConvertType, OptionResult optionResult);
}
