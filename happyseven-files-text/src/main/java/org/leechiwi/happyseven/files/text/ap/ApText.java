package org.leechiwi.happyseven.files.text.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.ApDocProxy;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.text.AbstractText;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.*;
import java.util.Objects;
@Slf4j
public class ApText extends AbstractText {
    private  ApDocProxy apDoc;
    private  Object in;
    private ResultOptions resultOptions;

    public ApText(Object in,ResultOptions resultOptions) {
        this.in = in;
        this.resultOptions=resultOptions;
        apDoc=new ApDocProxy(300,null,this.resultOptions);
    }
    public ApText(Object in) {
        this(in,ResultOptions.NONE);
    }
    @Override
    public boolean doPre(TextConvertType textConvertType) {
        return true;
    }

    @Override
    public boolean doConvert(Object in, OutputStream out, TextConvertType textConvertType, OptionResult optionResult) {
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
            //this.apDoc=new ApDocProxy(300,null,this.resultOptions);
            this.apDoc.createTextDoc(stringBuilder.toString());
            this.apDoc.convertAll(out,wordConvertType,optionResult);
        } catch (IOException e) {
            log.error("aspose text convert error",e);
            result=false;
        }
        return result;
    }
}
