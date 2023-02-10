package org.leechiwi.happyseven.files.text.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.text.AbstractText;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.*;
import java.util.Objects;
@Slf4j
public class ApText extends AbstractText {
    private  ApDoc apDoc = new ApDoc();
    private  Object in;

    public ApText(Object in) {
        this.in = in;
    }

    @Override
    public boolean doPre(TextConvertType textConvertType) {
        return true;
    }

    @Override
    public boolean doConvert(Object in, OutputStream out, TextConvertType textConvertType) {
        boolean result=true;
        try {
            if(Objects.isNull(in)){
                in=this.in;
            }
            InputStream inputStream=new FileRead().loadFile(in);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String lineStr = "";
            while ((lineStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineStr).append(System.getProperty("line.separator"));
            }
            WordConvertType wordConvertType = WordConvertType.valueOf(textConvertType.toString());
            this.apDoc.createTextDoc(stringBuilder.toString());
            this.apDoc.convertAll(out,wordConvertType);
        } catch (IOException e) {
            log.error("aspose text convert error",e);
            result=false;
        }
        return result;
    }
}
