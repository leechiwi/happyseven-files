package org.leechiwi.happyseven.files.ofd.rw;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.ofd.Ofd;
import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;
import org.leechiwi.happyseven.files.ofd.rw.convert.RwOfdConvert;
import org.leechiwi.happyseven.files.ofd.rw.factory.RwOfdConvertTypeFactory;

import java.io.OutputStream;

@Slf4j
public class RwOfd implements Ofd {
    @Override
    public boolean convertAll(Object in, OutputStream out, OfdConvertType ofdConvertType) {
        RwOfdConvert rwOfdConvert = new RwOfdConvertTypeFactory().convertOfdConvertType(ofdConvertType);
        return rwOfdConvert.convert(in,out);
    }
}
