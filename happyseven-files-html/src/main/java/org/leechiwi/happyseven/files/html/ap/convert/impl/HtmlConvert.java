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
public class HtmlConvert extends AbstractApHtmlConvert {
    protected  String in;
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
            SaveOptions.accept(this,htmlConvertType);
        } catch (HappysevenException e) {
            log.error("html converted to "+htmlConvertType.getExt()+" by aspose-html error",e);
            return false;
        }
        return true;
    }
    @Override
    public boolean convertTo(PdfSaveOptions pdfSaveOptions){
        Converter.convertHTML(this.in,pdfSaveOptions,this.out);
        return true;
    }
    @Override
    public boolean convertTo(ImageSaveOptions imageSaveOptions){
        Converter.convertHTML(this.in,imageSaveOptions,this.out);
        return true;
    }
    @Override
    public boolean convertTo(MHTMLSaveOptions MHTMLSaveOptions){
        Converter.convertHTML(this.in,MHTMLSaveOptions,this.out);
        return true;
    }
    @Override
    public boolean convertTo(XpsSaveOptions xpsSaveOptions){
        Converter.convertHTML(this.in,xpsSaveOptions,this.out);
        return true;
    }
    @Override
    public boolean convertTo(MarkdownSaveOptions markdownSaveOptions){
        Converter.convertHTML(this.in,markdownSaveOptions,this.out);
        return true;
    }

    @Override
    public boolean convertTo(HtmlConvertType fileConvertType) {
        throw new HappysevenException("html can not be converted to html by aspose-html");
    }
}
