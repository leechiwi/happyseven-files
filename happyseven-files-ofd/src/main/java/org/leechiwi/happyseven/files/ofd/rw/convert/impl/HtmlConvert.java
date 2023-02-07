package org.leechiwi.happyseven.files.ofd.rw.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.leechiwi.happyseven.files.ofd.rw.convert.AbstractRwOfdConvert;
import org.ofdrw.converter.HtmlMaker;
import org.ofdrw.reader.OFDReader;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class HtmlConvert extends AbstractRwOfdConvert {
    private int screenWidth;

    public HtmlConvert(int screenWidth) {
        this.screenWidth = screenWidth;
    }
    public HtmlConvert() {
        this(1000);
    }
    @Override
    public void doPre() {

    }

    @Override
    public boolean doConvert(Object in, OutputStream out) {
        boolean result = true;
        OFDReader reader = null;
        try {
            reader = createReader(in);
            HtmlMaker htmlMaker = new HtmlMaker(reader, null, this.screenWidth);
            String html = htmlMaker.doParse();
            IOUtils.write(html.getBytes(),out);
        } catch (Exception e) {
            log.error("OFDRW HtmlConvert failed", e);
            result = false;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("close OFDReader failed", e);
            }
        }
        return result;
    }
}
