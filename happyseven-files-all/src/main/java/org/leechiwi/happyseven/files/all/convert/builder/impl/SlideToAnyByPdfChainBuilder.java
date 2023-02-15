package org.leechiwi.happyseven.files.all.convert.builder.impl;

import org.leechiwi.happyseven.files.all.convert.ConvertChainHandler;
import org.leechiwi.happyseven.files.all.convert.builder.ChainBuilder;
import org.leechiwi.happyseven.files.all.convert.handlers.PdfToAnyHandler;
import org.leechiwi.happyseven.files.all.convert.handlers.SlideToPdfHandler;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

public class SlideToAnyByPdfChainBuilder implements ChainBuilder<PdfConvertType> {
    @Override
    public ConvertChainHandler<PdfConvertType> build() {
        ConvertChainHandler.Builder builder=new ConvertChainHandler.Builder();
        ConvertChainHandler<PdfConvertType> firstHandler = builder.addHandler(new SlideToPdfHandler()).addHandler(new PdfToAnyHandler()).build();
        return firstHandler;
    }
}
