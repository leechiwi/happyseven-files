package org.leechiwi.happyseven.files.ofd.rw.convert.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.ofd.rw.convert.AbstractRwOfdConvert;
import org.ofdrw.converter.FontLoader;
import org.ofdrw.converter.GeneralConvertException;
import org.ofdrw.converter.ItextMaker;
import org.ofdrw.converter.PdfboxMaker;
import org.ofdrw.reader.OFDReader;
import org.ofdrw.reader.PageInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class PdfConvert extends AbstractRwOfdConvert {
    public static PdfConvert.Lib lib = Lib.iText;

    public static enum Lib {
        iText, PDFBox
    }

    private OFDReader reader;

    public PdfConvert() {
    }

    public PdfConvert(Object in) {
        this.reader = createReader(in);
    }

    @Override
    public void doPre() {
        FontLoader.getInstance()
                .addAliasMapping("小标宋体", "方正小标宋简体")
                .addAliasMapping("KaiTi_GB2312", "楷体")
                .addAliasMapping("楷体", "KaiTi")

                .addSimilarFontReplaceRegexMapping(".*Kai.*", "楷体")
                .addSimilarFontReplaceRegexMapping(".*Kai.*", "楷体")
                .addSimilarFontReplaceRegexMapping(".*MinionPro.*", "SimSun")
                .addSimilarFontReplaceRegexMapping(".*SimSun.*", "SimSun")
                .addSimilarFontReplaceRegexMapping(".*Song.*", "宋体")
                .addSimilarFontReplaceRegexMapping(".*MinionPro.*", "SimSun");

        FontLoader.getInstance().scanFontDir(new File("src/main/resources/fonts"));
        FontLoader.setSimilarFontReplace(true);
    }

    @Override
    public boolean doConvert(Object in, OutputStream out, OptionResult optionResult) {
        boolean result = true;
        OFDReader reader = null;
        try {
            reader = createReader(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            switch (lib) {
                case iText: {
                    try (PdfWriter pdfWriter = new PdfWriter(bos);
                         PdfDocument pdfDocument = new PdfDocument(pdfWriter);) {
                        long start;
                        long end;
                        int pageNum = 1;
                        ItextMaker pdfMaker = new ItextMaker(reader);
                        for (PageInfo pageInfo : reader.getPageList()) {//到此处加载了每一页的页面大小但并没有加载内容（Contents.xml）
                            start = System.currentTimeMillis();
                            pdfMaker.makePage(pdfDocument, pageInfo);//此处开始加载具体内容
                            end = System.currentTimeMillis();
                            log.debug(String.format("page %d speed time %d", pageNum++, end - start));
                        }
                    }
                    break;
                }
                case PDFBox: {
                    try (PDDocument pdfDocument = new PDDocument();) {
                        PdfboxMaker pdfMaker = new PdfboxMaker(reader, pdfDocument);
                        long start = 0, end = 0, pageNum = 1;
                        for (PageInfo pageInfo : reader.getPageList()) {
                            start = System.currentTimeMillis();
                            pdfMaker.makePage(pageInfo);
                            end = System.currentTimeMillis();
                            log.debug(String.format("page %d speed time %d", pageNum++, end - start));
                        }
                        pdfDocument.save(bos);
                    }
                }
            }
            bos.writeTo(out);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("convert to pdf failed", e);
            throw new GeneralConvertException(e);
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
