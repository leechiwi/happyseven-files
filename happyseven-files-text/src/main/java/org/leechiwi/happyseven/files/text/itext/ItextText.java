package org.leechiwi.happyseven.files.text.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.text.AbstractText;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
@Slf4j
public class ItextText extends AbstractText {
    private  Object in;

    public ItextText(Object in) {
        this.in = in;
    }
    @Override
    public boolean doPre(TextConvertType textConvertType) {
        return TextConvertType.PDF==textConvertType;
    }

    @Override
    public boolean doConvert(Object in, OutputStream out, TextConvertType textConvertType) {
        boolean result=true;
        Document document =null;
        try {
            if(Objects.isNull(in)){
                in=this.in;
            }
            document=new Document();
            InputStream inputStream=new FileRead().loadFile(in);
            PdfWriter.getInstance(document, out);
            document.open();
            //BaseFont bfComic = BaseFont.createFont(file.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfComic = BaseFont.createFont();
            float fontsize = 12f;
            Font font = new Font(bfComic, fontsize, Font.NORMAL);
            List<String> list = IOUtils.readLines(inputStream,"utf-8");
            for (String line : list) {
                document.add(new Paragraph(line, font));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            result=false;
        } catch (IOException e) {
            e.printStackTrace();
            result=false;
        } finally {
            try {
                document.close();
            } catch (Exception e) {
                log.error("ItextText close document fail",e);
            }
        }
        return result;
    }
}
