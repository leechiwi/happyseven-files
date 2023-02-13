package org.leechiwi.happyseven.files.doc.ap;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.ap.factory.ApWordConvertTypeFactory;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.*;
import java.util.Objects;

@Slf4j
public class ApDoc extends DocLicense implements Doc<Document> {
    private Document document;

    public ApDoc() {

    }

    public ApDoc(Object document) {
        this.document = createDocument(document);
    }

    private Document createDocument(Object document) {
        Document doc = null;
        try {
            if(Objects.isNull(document)){
                return new Document();
            }
            if(document instanceof Document){
                return (Document)document;
            }
            InputStream inputStream = new FileRead().loadFile(document);
            doc = new Document(inputStream);
        } catch (Exception e) {
            log.error("create aspose word Document error", e);
            //throw new HappysevenException("create aspose word Document error", e);
        }
        return doc;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertType) {
        try {
            Document doc = new Document(in);
            doc.save(out, new ApWordConvertTypeFactory().convertWordConvertType(wordConvertType));
        } catch (Exception e) {
            log.error("aspose word convert InputStream error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(File file, OutputStream out, WordConvertType wordConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("aspose word convert file error", e);
        }
        return convert(in, out, wordConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, WordConvertType wordConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            log.error("aspose word convert filepath error", e);
        }
        return convert(in, out, wordConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, WordConvertType wordConvertType, OptionResult optionResult) {
        try {
            this.document.save(out, new ApWordConvertTypeFactory().convertWordConvertType(wordConvertType));
        } catch (Exception e) {
            log.error("aspose word convert all error", e);
            return false;
        }
        return true;
    }

    @Override
    public Document createTextDoc(String text) {
        try {
            this.document = new Document();
            DocumentBuilder builder = new DocumentBuilder(this.document);
            builder.write(text);
        } catch (Exception e) {
            log.error("aspose word create TextDoc error", e);
            return null;
        }
        return this.document ;
    }

    @Override
    public Document getDoc() {
        return this.document;
    }
}
