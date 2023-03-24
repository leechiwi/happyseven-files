package org.leechiwi.happyseven.files.pdf.itextpdf;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.read.FileRead;
import org.leechiwi.happyseven.files.pdf.Pdf;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class ItextPdf implements Pdf<InputStream> {
    private InputStream inputStream;
    private PdfTemplateElement pdfTemplateElement;

    public ItextPdf(Object in,PdfTemplateElement pdfTemplateElement) {
        this.inputStream = createDocument(in);
        this.pdfTemplateElement=pdfTemplateElement;
    }
    private InputStream createDocument(Object in) {
        InputStream inputStream=null;
        try {
            inputStream = new FileRead().loadFile(in);
        } catch (Exception e) {
            log.error("itextpdf Document error", e);
        }
        return inputStream;
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, PdfConvertType pdfConvertType) {
        return generatePdf(in,this.pdfTemplateElement.getDataMap(),out);
    }

    @Override
    public boolean convert(File file, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream inputStream = this.createDocument(file);
        return convert(inputStream,out,pdfConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, PdfConvertType pdfConvertType) {
        InputStream inputStream = this.createDocument(path);
        return convert(inputStream,out,pdfConvertType);
    }

    @Override
    public InputStream getDoc() {
        return this.inputStream;
    }

    @Override
    public boolean convertAll(OutputStream out, PdfConvertType fileConvertType, OptionResult optionResult) {
        return generatePdf(this.inputStream,this.pdfTemplateElement.getDataMap(),out);
    }
    private boolean generatePdf(InputStream inputStream,Map<String,String> dataMap,OutputStream out){
        // 1.指定解析器
        //System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
        // "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            // 2 读入pdf表单
            reader = new PdfReader(inputStream);
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
