package org.leechiwi.happyseven.files.slide.ap;

import com.aspose.slides.Presentation;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.slide.Slide;
import org.leechiwi.happyseven.files.slide.enums.SlideConvertType;

import java.io.*;

@Slf4j
public class ApSlide extends SlideLicense implements Slide {
    private Presentation slide;
    public ApSlide(){

    }
    public ApSlide(Object document) {
        this.slide=createDocument(document);
    }
    private Presentation createDocument(Object document) {
        Presentation slide=null;
        try {
            InputStream inputStream = new FileRead().loadFile(document);
            slide=new Presentation(inputStream);
        } catch (Exception e) {
            log.error("create aspose slide Document error");
            //throw new HappysevenException("create aspose slide Document error", e);
        }
        return slide;
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, SlideConvertType slideConvertType) {
        try {
            Presentation slide = new Presentation(in);
            slide.save(out, slideConvertType.getCode());
        } catch (Exception e) {
            log.error("aspose slide convert InputStream error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(File file, OutputStream out, SlideConvertType slideConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("aspose slide convert file error",e);
        }
        return convert(in,out,slideConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, SlideConvertType slideConvertType) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            log.error("aspose slide convert filepath error",e);
        }
        return convert(in,out,slideConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, SlideConvertType slideConvertType, OptionResult optionResult) {
        try {
            this.slide.save(out,slideConvertType.getCode());
        } catch (Exception e) {
            log.error("aspose slide load all error",e);
            return false;
        }
        return true;
    }
}
