package org.leechiwi.happyseven.files.all.convert.handlers.impl;

import org.leechiwi.happyseven.files.all.convert.handlers.ConvertChainHandler;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfInnerImagesProxy;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.OutputStream;
import java.util.List;

public class PdfInnerImagesHandler extends ConvertChainHandler {
    @Override
    public boolean doHandler(Object in, OutputStream out, Object fileConvertType, ResultOptions resultOptions, OptionResult optionResult) {
        boolean convert = new ApPdfInnerImagesProxy(in, resultOptions,true).convertAll(out, PdfConvertType.JPEG,optionResult);
        this.nextIn=optionResult;
        return convert;
    }

    @Override
    public Object doHandlerPost(Object object) {
        List list = ((OptionResult) object).getList();
        return list;
    }
}
