package org.leechiwi.happyseven.files.all.convert.handlers.impl;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.all.convert.handlers.ConvertChainHandler;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfProxy;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Slf4j
public class PdfToAnyHandler extends ConvertChainHandler<PdfConvertType> {

    @Override
    public boolean doHandler(Object in, OutputStream out, PdfConvertType fileConvertType, ResultOptions resultOptions, OptionResult optionResult) {
        boolean convert = new ApPdfProxy(300, in, resultOptions).convertAll(out, fileConvertType, optionResult);
        return convert;
    }

    @Override
    public Object doHandlerPost(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) object;
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
