package org.leechiwi.happyseven.files.html.ap.convert.impl;

import com.aspose.html.converters.Converter;
import com.aspose.html.saving.*;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.html.ap.convert.AbstractApHtmlConvert;
import org.leechiwi.happyseven.files.html.ap.convert.SaveOptions;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;
@Slf4j
public class MarkdownConvert extends AbstractApHtmlConvert {
    private String in;
    @Override
    protected boolean doPre(Object in) {
        boolean result = this.inIsString(in);
        if(result){
            this.in=(String)in;
        }
        return result;
    }

    @Override
    protected boolean doConvert(Object in, String out, HtmlConvertType htmlConvertType, OptionResult optionResult) {
        try {
            SaveOptions.accept(this, htmlConvertType);
        } catch (HappysevenException e) {
            log.error("markdown converted to " + htmlConvertType.getExt() + " by aspose-html error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convertTo(PdfSaveOptions pdfSaveOptions) {
        throw new HappysevenException("markdown can not be converted to pdf by aspose-html");
    }

    @Override
    public boolean convertTo(ImageSaveOptions imageSaveOptions) {
        throw new HappysevenException("markdown can not be converted to iamge by aspose-html");
    }

    @Override
    public boolean convertTo(MHTMLSaveOptions MHTMLSaveOptions) {
        throw new HappysevenException("markdown can not be converted to mhtml by aspose-html");
    }

    @Override
    public boolean convertTo(XpsSaveOptions xpsSaveOptions) {
        throw new HappysevenException("markdown can not be converted to xps by aspose-html");
    }

    @Override
    public boolean convertTo(MarkdownSaveOptions markdownSaveOptions) {
        throw new HappysevenException("markdown can not be converted to markdown by aspose-html");
    }

    @Override
    public boolean convertTo(HtmlConvertType fileConvertType) {
        Converter.convertMarkdown(this.in, this.out);
        return true;
    }
}
