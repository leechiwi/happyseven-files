package org.leechiwi.happyseven.files.all.convert.handlers.impl;

import org.leechiwi.happyseven.files.all.convert.handlers.ConvertChainHandler;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;
import org.leechiwi.happyseven.files.slide.ap.ApSlide;
import org.leechiwi.happyseven.files.slide.enums.SlideConvertType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SlideToPdfHandler extends ConvertChainHandler<PdfConvertType> {
    @Override
    public boolean doHandler(Object in, OutputStream out, PdfConvertType fileConvertType, ResultOptions resultOptions, OptionResult optionResult) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        boolean convert = new ApSlide(in).convertAll(output, SlideConvertType.Pdf, optionResult);
        this.nextIn = output;
        return convert;
    }

    @Override
    public Object doHandlerPost(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) object;
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
