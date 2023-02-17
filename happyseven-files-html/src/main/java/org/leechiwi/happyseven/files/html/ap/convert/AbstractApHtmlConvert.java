package org.leechiwi.happyseven.files.html.ap.convert;

import com.aspose.html.saving.*;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;

import java.io.InputStream;

public abstract class AbstractApHtmlConvert implements ApHtmlConvert {
    protected  String out;
    protected abstract boolean doPre(Object in);

    protected abstract boolean doConvert(Object in, String out, HtmlConvertType fileConvertType,OptionResult optionResult);

    public abstract boolean convertTo(PdfSaveOptions pdfSaveOptions);

    public abstract boolean convertTo(ImageSaveOptions imageSaveOptions);

    public abstract  boolean convertTo(MHTMLSaveOptions MHTMLSaveOptions);

    public abstract boolean convertTo(XpsSaveOptions xpsSaveOptions);

    public abstract boolean convertTo(MarkdownSaveOptions markdownSaveOptions);

    public abstract boolean convertTo(HtmlConvertType fileConvertType);
    @Override
    public boolean convert(Object in, String out, HtmlConvertType htmlConvertType, OptionResult optionResult) {
        if(!this.doPre(in)){
            return false;
        }
        this.out=out;
        return this.doConvert(in, out, htmlConvertType,optionResult);
    }

    protected   boolean inIsString(Object in){
        if(in  instanceof String){
            return true;
        }
        return false;
    }

    protected boolean inIsStream(Object in){
        if(in  instanceof InputStream){
            return true;
        }
        return false;
    }
}
