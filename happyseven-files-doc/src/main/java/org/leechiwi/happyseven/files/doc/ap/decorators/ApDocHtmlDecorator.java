package org.leechiwi.happyseven.files.doc.ap.decorators;

import com.aspose.words.Document;
import com.aspose.words.HtmlFixedSaveOptions;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveOptions;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class ApDocHtmlDecorator implements Doc<Document> {
    private Doc<Document> apDoc;
    private boolean embeddedHtml;
    public ApDocHtmlDecorator(Doc<Document> apDoc,boolean embeddedHtml) {
        this.apDoc = apDoc;
        this.embeddedHtml=embeddedHtml;
    }

    public ApDocHtmlDecorator(Doc<Document> apDoc) {
        this(apDoc,false);
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertType) {
        if (this.toHtml(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(in,out,wordConvertType);
    }

    @Override
    public boolean convert(File file, OutputStream out, WordConvertType wordConvertType) {
        if (this.toHtml(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(file,out,wordConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, WordConvertType wordConvertType) {
        if (this.toHtml(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(path,out,wordConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, WordConvertType wordConvertType, OptionResult optionResult) {
        if (this.toHtml(wordConvertType)) {
            return this.convertToHtml(out,wordConvertType);
        }
        return this.apDoc.convertAll(out, wordConvertType, optionResult);
    }

    @Override
    public Document createTextDoc(String text) {
        return this.apDoc.createTextDoc(text);
    }

    @Override
    public Document getDoc() {
        return this.apDoc.getDoc();
    }

    private boolean toHtml(WordConvertType wordConvertType) {
        return WordConvertType.HTML_FIXED.equals(wordConvertType) || WordConvertType.HTML.equals(wordConvertType);
    }

    private boolean convertToHtml(OutputStream out,WordConvertType wordConvertType){
        try {
            SaveOptions saveOptions = this.getSaveOptions(wordConvertType);
            if(Objects.isNull(saveOptions)){
                return false;
            }
            this.apDoc.getDoc().save(out,saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private SaveOptions getSaveOptions(WordConvertType wordConvertType){
        if (WordConvertType.HTML_FIXED.equals(wordConvertType)) {
            HtmlFixedSaveOptions saveOptions = new HtmlFixedSaveOptions();
            saveOptions.setExportEmbeddedImages(this.embeddedHtml);
            saveOptions.setExportEmbeddedFonts(this.embeddedHtml);
            saveOptions.setExportEmbeddedCss(this.embeddedHtml);
            saveOptions.setExportEmbeddedSvg(this.embeddedHtml);
            return saveOptions;
        } else if (WordConvertType.HTML.equals(wordConvertType)) {
            HtmlSaveOptions saveOptions = new HtmlSaveOptions();
            saveOptions.setExportImagesAsBase64(this.embeddedHtml);
            saveOptions.setExportFontsAsBase64(this.embeddedHtml);
            return saveOptions;
        } else {
            return null;
        }
    }
}
