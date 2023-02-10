package org.leechiwi.happyseven.files.ofd.rw;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.ofd.Ofd;
import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;
import org.leechiwi.happyseven.files.ofd.rw.convert.RwOfdConvert;
import org.leechiwi.happyseven.files.ofd.rw.factory.RwOfdConvertTypeFactory;

import java.io.OutputStream;

@Slf4j
public class RwOfd implements Ofd {
    private ResultOptions resultOptions;

    public RwOfd(ResultOptions resultOptions) {
        this.resultOptions = resultOptions;
    }

    public RwOfd() {
        this(ResultOptions.NONE);
    }
    @Override
    public boolean convertAll(Object in, OutputStream out, OfdConvertType ofdConvertType, OptionResult optionResult) {
        RwOfdConvert rwOfdConvert = new RwOfdConvertTypeFactory(this.resultOptions).convertOfdConvertType(ofdConvertType);
        return rwOfdConvert.convert(in,out,optionResult);
    }
}
