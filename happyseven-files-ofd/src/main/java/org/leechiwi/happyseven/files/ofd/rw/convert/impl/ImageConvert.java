package org.leechiwi.happyseven.files.ofd.rw.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.util.Zip;
import org.leechiwi.happyseven.files.ofd.rw.convert.AbstractRwOfdConvert;
import org.ofdrw.converter.ImageMaker;
import org.ofdrw.reader.OFDReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImageConvert extends AbstractRwOfdConvert {
    @Override
    public void doPre() {

    }

    @Override
    public boolean doConvert(Object in, OutputStream out) {
        boolean result=true;
        OFDReader reader=null;
        try {
            reader= createReader(in);
            ImageMaker imageMaker = new ImageMaker(reader, 15);
            imageMaker.config.setDrawBoundary(false);
            List<byte[]> streamList=new ArrayList<>();
            for (int i = 0; i < imageMaker.pageSize(); i++) {
                BufferedImage image = imageMaker.makePage(i);
                //Path dist = Paths.get(dirPath, i + ".png");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG",byteArrayOutputStream );
                streamList.add(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();
            }
            Zip.zip(out,streamList,".png");
        } catch (Exception e) {
            log.error("ORDRW ImageConvert failed", e);
            result=false;
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
