package org.leechiwi.happyseven.files.all.convert.impl;

import org.leechiwi.happyseven.files.all.convert.builder.impl.SlideToAnyByPdfChainBuilder;
import org.leechiwi.happyseven.files.base.File;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.OutputStream;

public class SlideToAnyByPdf implements File<PdfConvertType> {
    private Object in;
    private ResultOptions resultOptions;
    private SlideToAnyByPdfChainBuilder slideToAnyByPdfChainBuilder;
    public SlideToAnyByPdf(Object in, ResultOptions resultOptions){
        this.in=in;
        this.resultOptions=resultOptions;
        this.slideToAnyByPdfChainBuilder = new SlideToAnyByPdfChainBuilder();
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        return slideToAnyByPdfChainBuilder.build().start(this.in, out, fileConvertType, this.resultOptions, optionResult);
    }
}
