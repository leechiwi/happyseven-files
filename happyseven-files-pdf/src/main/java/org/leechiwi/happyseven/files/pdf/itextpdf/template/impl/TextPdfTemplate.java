package org.leechiwi.happyseven.files.pdf.itextpdf.template.impl;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.AbstractPdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.PdfTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class TextPdfTemplate extends AbstractPdfTemplate {

    public TextPdfTemplate(InputStream inputStream, PdfTemplateElement pdfTemplateElement) {
        super(inputStream,pdfTemplateElement);
    }

    @Override
    public boolean doCreate(OutputStream out) {
        // 1.指定解析器
        //System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
        // "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            // 2 读入pdf表单
            reader = new PdfReader(this.inputStream);
            // 3 根据表单生成一个新的pdf
            ps = new PdfStamper(reader, out);
            // 4 获取pdf表单
            AcroFields form = ps.getAcroFields();
            // 5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            /*BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            form.addSubstitutionFont(bf);*/
            //设置字体
            final BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<>();
            fontList.add(font);
            form.setSubstitutionFonts(fontList);
            // 6查询数据================================================
            // 7遍历data 给pdf表单表格赋值
            Map<String,String> dataMap = this.pdfTemplateElement.getDataMap();
            for (String key : dataMap.keySet()) {
                form.setField(key,dataMap.get(key));
            }
            ps.setFormFlattening(true);
        } catch (Exception e) {
            log.error("PDF export fail",e);
            return false;
        } finally {
            try {
                ps.close();
                reader.close();
            } catch (Exception e) {
                log.error("PDF file export close  fail",e);
            }
        }
        return true;

    }
}
