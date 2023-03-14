package org.leechiwi.happyseven.files.text.ap;

import com.aspose.words.Document;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.doc.Doc;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.proxy.ApDocHtmlProxy;
import org.leechiwi.happyseven.files.doc.ap.proxy.ApDocImageProxy;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocTextDecorator;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.text.AbstractText;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.*;
import java.util.Objects;
@Slf4j
public class ApWordText extends AbstractText {
    private Doc<Document> apDoc;
    private  Object in;
    private ResultOptions resultOptions;

    public ApWordText(Object in, ResultOptions resultOptions) {
        this.in = in;
        this.resultOptions=resultOptions;
        ApDocImageProxy apDocImageProxy = new ApDocImageProxy(300, new ApDoc(), this.resultOptions);
        ApDocHtmlProxy apDocHtmlProxy = new ApDocHtmlProxy(apDocImageProxy);
        apDoc =new ApDocTextDecorator(apDocHtmlProxy,in);
    }
    public ApWordText(Object in) {
        this(in,ResultOptions.NONE);
    }
    @Override
    public boolean doPre(TextConvertType textConvertType) {
        return true;
    }

    @Override
    public boolean doConvert(Object in, OutputStream out, TextConvertType textConvertType, OptionResult optionResult) {
            if(Objects.isNull(in)){
                in=this.in;
            }
            WordConvertType wordConvertType = WordConvertType.valueOf(textConvertType.toString());
            return this.apDoc.convertAll(out,wordConvertType,optionResult);
    }
}
