package org.leechiwi.happyseven.files.ofd.rw.factory;

import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.ofd.OfdConvertTypeFactory;
import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;
import org.leechiwi.happyseven.files.ofd.rw.convert.RwOfdConvert;
import org.leechiwi.happyseven.files.ofd.rw.convert.impl.HtmlConvert;
import org.leechiwi.happyseven.files.ofd.rw.convert.impl.ImageConvert;
import org.leechiwi.happyseven.files.ofd.rw.convert.impl.PdfConvert;
import org.leechiwi.happyseven.files.ofd.rw.convert.impl.SvgConvert;

import java.io.OutputStream;

public class RwOfdConvertTypeFactory implements OfdConvertTypeFactory<RwOfdConvert> {
    private ResultOptions resultOptions;

    public RwOfdConvertTypeFactory(ResultOptions resultOptions) {
        this.resultOptions = resultOptions;
    }

    @Override
    public RwOfdConvert convertOfdConvertType(OfdConvertType ofdConvertType) {
        RwOfdConvert rwOfdConvert=null;
        if(OfdConvertType.PDF==ofdConvertType){
            rwOfdConvert=new PdfConvert();
        }else if(OfdConvertType.SVG==ofdConvertType){
            rwOfdConvert=new SvgConvert(this.resultOptions);
        }else if(OfdConvertType.PNG==ofdConvertType){
            rwOfdConvert=new ImageConvert(this.resultOptions);
        }else if(OfdConvertType.HTML==ofdConvertType){
            rwOfdConvert= new HtmlConvert();
        }
        return rwOfdConvert;
    }
}
