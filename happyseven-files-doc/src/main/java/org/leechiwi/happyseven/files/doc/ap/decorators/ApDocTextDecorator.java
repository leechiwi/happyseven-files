package org.leechiwi.happyseven.files.doc.ap.decorators;

import com.aspose.words.Document;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.*;
@Slf4j
public class ApDocTextDecorator implements Doc<Document> {
    private Doc<Document> apDoc;
    private  Object in;

    public ApDocTextDecorator(Doc<Document> apDoc, Object in) {
        this.apDoc = apDoc;
        this.in = in;
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, WordConvertType wordConvertTypet) {
        return false;
    }

    @Override
    public boolean convert(File file, OutputStream out, WordConvertType wordConvertType) {
        return false;
    }

    @Override
    public boolean convert(String path, OutputStream out, WordConvertType wordConvertType) {
        return false;
    }

    @Override
    public Document createTextDoc(String text) {
        return this.apDoc.createTextDoc(text);
    }

    @Override
    public Document getDoc() {
        return this.apDoc.getDoc();
    }

    @Override
    public boolean convertAll(OutputStream out, WordConvertType fileConvertType, OptionResult optionResult) {
        boolean result=true;
        try {
            createTextDoc(this.in);
            this.apDoc.convertAll(out,fileConvertType,optionResult);
        } catch (HappysevenException e) {
            log.error("aspose text convert error",e);
            result=false;
        }
        return result;
    }
    private boolean createTextDoc(Object in) throws HappysevenException{
        try {
            InputStream inputStream=new FileRead().loadFile(in);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String lineStr = "";
            while ((lineStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineStr).append(System.getProperty("line.separator"));
            }
            createTextDoc(stringBuilder.toString());
        } catch (IOException e) {
            throw new HappysevenException("read text file error",e);
        }
        return true;
    }
}
