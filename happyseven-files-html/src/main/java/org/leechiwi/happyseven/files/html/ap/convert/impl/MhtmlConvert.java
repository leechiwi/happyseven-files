package org.leechiwi.happyseven.files.html.ap.convert.impl;

import com.aspose.html.converters.Converter;
import com.aspose.html.saving.*;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.html.ap.convert.AbstractApHtmlConvert;
import org.leechiwi.happyseven.files.html.ap.convert.SaveOptions;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Slf4j
public class MhtmlConvert  extends AbstractApHtmlConvert {
    private InputStream input;
    @Override
    protected boolean doPre(Object in) {
        try {
            if(this.inIsStream(in)){
                input=(InputStream) in;
                return true;
            }
            if(this.inIsString(in)){
                this.input=new FileInputStream((String)in);
                return true;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    @Override
    protected boolean doConvert(Object in, String out, HtmlConvertType htmlConvertType, OptionResult optionResult) {
        try {
            SaveOptions.accept(this, htmlConvertType);
        } catch (HappysevenException e) {
            log.error("mhtml converted to " + htmlConvertType.getExt() + " by aspose-html error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convertTo(PdfSaveOptions pdfSaveOptions) {
        Converter.convertMHTML(this.input, pdfSaveOptions, this.out);
        return true;
    }

    @Override
    public boolean convertTo(ImageSaveOptions imageSaveOptions) {
        Converter.convertMHTML(this.input, imageSaveOptions, this.out);
        return true;
    }

    @Override
    public boolean convertTo(MHTMLSaveOptions MHTMLSaveOptions) {
        throw new HappysevenException("mhtml can not be onverted to mhtml by aspose-html");
    }

    @Override
    public boolean convertTo(XpsSaveOptions xpsSaveOptions) {
        Converter.convertMHTML(this.input, xpsSaveOptions, this.out);
        return true;
    }

    @Override
    public boolean convertTo(MarkdownSaveOptions markdownSaveOptions) {
        throw new HappysevenException("mhtml can not be converted to markdown by aspose-html");
    }

    @Override
    public boolean convertTo(HtmlConvertType fileConvertType) {
        throw new HappysevenException("mhtml can not be converted to html by aspose-html");
    }
}
