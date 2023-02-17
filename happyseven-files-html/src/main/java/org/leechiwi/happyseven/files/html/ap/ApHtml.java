package org.leechiwi.happyseven.files.html.ap;

import com.aspose.html.HTMLDocument;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.html.Html;
import org.leechiwi.happyseven.files.html.ap.convert.ApHtmlConvert;
import org.leechiwi.happyseven.files.html.ap.factory.HtmlSourceFactory;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;
import org.leechiwi.happyseven.files.html.enums.SourceType;

import java.io.*;
@Slf4j
public class ApHtml extends HtmlLicense implements Html<HTMLDocument> {
    private Object in;
    private SourceType sourceType;
    private ApHtmlConvert apHtmlConvert;
    public ApHtml(Object in,SourceType sourceType){
        this.sourceType=sourceType;
        this.apHtmlConvert= HtmlSourceFactory.createSource(this.sourceType);
        this.in=in;
    }
    public ApHtml(Object in){
        this(in, SourceType.HTML);
    }
    @Override
    public boolean convertAll(OutputStream out, HtmlConvertType fileConvertType, OptionResult optionResult) {
      return false;
    }

    @Override
    public boolean convertAll(String out, HtmlConvertType fileConvertType, OptionResult optionResult) {
        return this.apHtmlConvert.convert(this.in,out,fileConvertType,optionResult);
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, HtmlConvertType htmlConvertTypet) {
        return false;
    }

    @Override
    public boolean convert(File file, OutputStream out, HtmlConvertType htmlConvertTypet) {
        return false;
    }

    @Override
    public boolean convert(String path, OutputStream out, HtmlConvertType htmlConvertTypet) {
        return false;
    }

    @Override
    public boolean convert(InputStream in, String out, HtmlConvertType htmlConvertTypet) {
        return this.apHtmlConvert.convert(in,out,htmlConvertTypet,null);
    }

    @Override
    public boolean convert(File file, String out, HtmlConvertType htmlConvertTypet) {
        return this.convert(file.getAbsolutePath(),out,htmlConvertTypet);
    }

    @Override
    public boolean convert(String path, String out, HtmlConvertType htmlConvertTypet) {
        return this.apHtmlConvert.convert(path,out,htmlConvertTypet,null);
    }
}
