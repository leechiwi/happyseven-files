package org.leechiwi.happyseven.files.html.ap.factory;

import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.html.ap.convert.ApHtmlConvert;
import org.leechiwi.happyseven.files.html.ap.convert.impl.EpubConvert;
import org.leechiwi.happyseven.files.html.ap.convert.impl.HtmlConvert;
import org.leechiwi.happyseven.files.html.ap.convert.impl.MarkdownConvert;
import org.leechiwi.happyseven.files.html.ap.convert.impl.SvgConvert;
import org.leechiwi.happyseven.files.html.enums.SourceType;

public class HtmlSourceFactory {
    public static ApHtmlConvert createSource(SourceType sourceType) throws HappysevenException {
        if (SourceType.HTML == sourceType) {
            return new HtmlConvert();
        }else if(SourceType.EPUB==sourceType){
            return new EpubConvert();
        }else if(SourceType.SVG==sourceType){
            return new SvgConvert();
        }else if(SourceType.MARKDOWN==sourceType){
            return new MarkdownConvert();
        }else if(SourceType.MHTML==sourceType){
            return new MarkdownConvert();
        }else{
            throw new HappysevenException("unsupport htmlsource");
        }
    }
}
