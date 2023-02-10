package org.leechiwi.happyseven.files.doc.ap;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.base.util.Result;
import org.leechiwi.happyseven.files.base.util.Zip;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.ap.factory.ApWordConvertTypeFactory;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ApDocProxy implements Doc {
    private int dpi;
    private ApDoc apDoc;
    private ResultOptions resultOptions;

    public ApDocProxy(int dpi, Object in,ResultOptions resultOptions) {
        if (Objects.nonNull(in)) {
            this.apDoc = new ApDoc(in);
        } else {
            this.apDoc = new ApDoc();
        }
        this.resultOptions=resultOptions;
        this.dpi = dpi;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertType) {
        if (this.toImage(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(in, out, wordConvertType);
    }

    @Override
    public boolean convert(File file, OutputStream out, WordConvertType wordConvertType) {
        if (this.toImage(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(file, out, wordConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, WordConvertType wordConvertType) {
        if (this.toImage(wordConvertType)) {
            return false;
        }
        return this.apDoc.convert(path, out, wordConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, WordConvertType wordConvertType, OptionResult optionResult) {
        if (this.toImage(wordConvertType)) {
            return this.convertToImg(wordConvertType, out,optionResult);
        }
        //optionResult=null;
        return this.apDoc.convertAll(out, wordConvertType,optionResult);
    }

    @Override
    public Document createTextDoc(String text) {
        return this.apDoc.createTextDoc(text);
    }

    private boolean toImage(WordConvertType wordConvertType) {
        return WordConvertType.getImageTypeSet().contains(wordConvertType);
    }

    private boolean convertToImg(WordConvertType wordConvertType, OutputStream out,OptionResult optionResult) {
        try {
            List<byte[]> list = new ArrayList<>();
            ImageSaveOptions imageSaveOptions = new ImageSaveOptions(new ApWordConvertTypeFactory().convertWordConvertType(wordConvertType));
            imageSaveOptions.setResolution(this.dpi);
            Document doc = this.apDoc.getDocument();
            for (int i = 0, size = doc.getPageCount(); i < size; i++) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                imageSaveOptions.setPageIndex(i);
                doc.save(os, imageSaveOptions);
                list.add(os.toByteArray());
            }
            Result.convertToImageResult(resultOptions,wordConvertType.getExt(),out,list,optionResult);
        } catch (Exception e) {
            log.error("aspose word convert image file error", e);
            return false;
        }
        return true;
    }
}
