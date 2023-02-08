package org.leechiwi.happyseven.files.ofd.rw.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.util.Zip;
import org.leechiwi.happyseven.files.ofd.rw.convert.AbstractRwOfdConvert;
import org.ofdrw.converter.SVGMaker;
import org.ofdrw.reader.OFDReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SvgConvert extends AbstractRwOfdConvert {
    @Override
    public void doPre() {

    }

    @Override
    public boolean doConvert(Object in, OutputStream out) {
        OFDReader reader=null;
        try {
            reader= createReader(in);
            SVGMaker svgMaker = new SVGMaker(reader, 5d);
            svgMaker.config.setDrawBoundary(false);
            svgMaker.config.setClip(false);
            List<byte[]> streamList=new ArrayList<>();
            for (int i = 0; i < svgMaker.pageSize(); i++) {
                String svg = svgMaker.makePage(i);
                streamList.add(svg.getBytes());
            }
            Zip.zip(out,streamList,".svg");
        }catch(Exception e){
            log.error("OFDRW SvgConvert failed", e);
            return false;
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("close OFDReader failed", e);
            }
        }
        return true;
    }
}
