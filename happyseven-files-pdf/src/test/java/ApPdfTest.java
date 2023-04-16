import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.ap.ApPdf;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfInnerImagesProxy;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfProxy;
import org.leechiwi.happyseven.files.pdf.ap.decorators.ApPdfImagesDecorator;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;
import org.leechiwi.happyseven.files.pdf.itextpdf.ItextPdf;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.RowAndColSpan;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation.PdfField;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfTemplateType;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.proxy.TablePdfTemplateProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApPdfTest {
    @Data
    private static class DemoBean {
        @PdfField(order = 1)
        private Integer index;
        @PdfField(order = 3)
        private Integer age;
        @PdfField(order = 2)
        private String name;
        @PdfField(order = 4)
        private Date birthDay;
        @PdfField(order = 5, label = "电话1")
        private String phone1;
        @PdfField(order = 6, label = "电话2")
        private String phone2;
        @PdfField(order = 7)
        private String phone3;
        @PdfField(order = 8)
        private String moubilPhone1;
        @PdfField(order = 9)
        private String moubilPhone2;
        @PdfField(order = 10)
        private String moubilPhone3;
        @PdfField(order = 11)
        private String moubilPhone4;
        @PdfField(order = 12)
        private String moubilPhone5;
        @PdfField(order = 13, label = "地址")
        private String address1;
        @PdfField(order = 14)
        private String address2;
        @PdfField(order = 15)
        private String doBest1;
        @PdfField(order = 16)
        private String doBest2;
        @PdfField(order = 17)
        private String doBest3;

    }

    @Test
    public void convert() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/test1.doc"));
            boolean convert = new ApPdfProxy(300, "d:/test.pdf", ResultOptions.NONE).convertAll(out, PdfConvertType.DOC, null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Test
    public void ImagesConvert() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            List imageList = Stream.of("d:/微信图片_20221031143341.jpg", "d:/微信图片_20230111153127.jpg").collect(Collectors.toList());
            //List imageList= Stream.of("d:/微信图片_20221017161933.jpg","d:/c203d6c5cd19e8b8b392c28ce922916.jpg").collect(Collectors.toList());
            ApPdfImagesDecorator apPdfImagesDecorator = new ApPdfImagesDecorator(imageList, new ApPdf(null));
            boolean convert = apPdfImagesDecorator.convertAll(out, PdfConvertType.PDF, new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Test
    public void InnerImagesConvert() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/test.zip"));
            boolean convert = new ApPdfInnerImagesProxy("d:/test.pdf", ResultOptions.ALL_IN_ZIP, true).convertAll(out, PdfConvertType.JPEG, null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Test
    public void convertWithPdfTemplate() {
        FileOutputStream out = null;
        try {
            //-----------------------------模板根据key填充------------------------------------------
            /*Map<String, String> data = new HashMap<String, String>();
            data.put("bt", "最高人民法院协助调查通知书");
            data.put("ah", "(2018)最高法民终xxxx号");
            data.put("fy", "最高人民法院");
            data.put("ah1", "(2018)最高法民终xxxx号");
            data.put("dsr", "张奕 等");
            data.put("dsrxx", "张奕，性别：男性，身份证号：xxxxxxxxxxxxxxxxxx;");
            data.put("fg","张法官");
            data.put("fgdh", "13212345678");
            data.put("rq", "2023年03月30日");
            PdfTemplateElement pdfTemplateElement=new PdfTemplateElement();
            pdfTemplateElement.setDataMap(data);
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert =new ItextPdf("d:/pdf.pdf",pdfTemplateElement, PdfTemplateType.PURE_TEXT).convertAll(out,PdfConvertType.PDF,null);
            System.out.println("result=" + convert);*/
            //-----------------------------跨行跨列的表格-------------------------------------------
     /*       PdfTemplateElement pdfTemplateElement=new PdfTemplateElement();
            List<RowAndColSpan> list = new ArrayList<>();
            RowAndColSpan rc1 = new RowAndColSpan(1,2,2,3);
            RowAndColSpan rc2 = new RowAndColSpan(1,2,4,5);
            RowAndColSpan rc3 = new RowAndColSpan(4,5,2,2);
            RowAndColSpan rc4 = new RowAndColSpan(5,6,4,5);
            list.add(rc1);
            list.add(rc2);
            list.add(rc3);
            list.add(rc4);
            pdfTemplateElement.setRowAndColSpanList(list);
            float[] width={30,30,40,40,30};
            pdfTemplateElement.setColumnWidth(width);
            List<String> list1 = Stream.of("1", "2", "3").collect(Collectors.toList());
            List<String> list2 = Stream.of("4").collect(Collectors.toList());//, "5", "6"
            List<String> list3 = Stream.of("7","8", "9", "10", "11").collect(Collectors.toList());
            List<String> list4 = Stream.of("12","13", "14", "15", "16").collect(Collectors.toList());
            List<String> list5 = Stream.of("17","19", "20").collect(Collectors.toList());//, "18", "21"
            List<String> list6 = Stream.of("22","23", "24").collect(Collectors.toList());//, "25", "26"
            List<List<String>> allRowList = new ArrayList<>();
            allRowList.add(list1);
            allRowList.add(list2);
            allRowList.add(list3);
            allRowList.add(list4);
            allRowList.add(list5);
            allRowList.add(list6);
            pdfTemplateElement.setTableDataList(allRowList);
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert =new ItextPdf("d:/blank.pdf",pdfTemplateElement, PdfTemplateType.TABLE).convertAll(out,PdfConvertType.PDF,null);
            System.out.println("result=" + convert);*/
            //------------------------------常规表格------------------------------------------
/*            PdfTemplateElement pdfTemplateElement=new PdfTemplateElement();
            float[] width={30,30,40,40,30};
            List<String> header = Stream.of("序号","姓名", "年龄", "生日", "备注").collect(Collectors.toList());
            List<List<String>> allRowList = new ArrayList<>();
            allRowList.add(header);
                List<String> dataList =null;
            for (int i = 0; i < 50; i++) {
                if(i%5==0){
                    dataList = new ArrayList<>();
                }
                dataList.add((i+1)+"");
                if(dataList.size()==5){
                    allRowList.add(dataList);
                }
            }
            pdfTemplateElement.setColumnWidth(width);
            pdfTemplateElement.setTableDataList(allRowList);
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert =new ItextPdf("d:/blank.pdf",pdfTemplateElement, PdfTemplateType.TABLE).convertAll(out,PdfConvertType.PDF,null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }*/
            //-----------------------------跨行跨列的表格实体-------------------------------------------
            PdfTemplateElement pdfTemplateElement = new PdfTemplateElement();
            List<RowAndColSpan> list = new ArrayList<>();
            RowAndColSpan rc1 = new RowAndColSpan(1, 2, 2, 3);
            RowAndColSpan rc2 = new RowAndColSpan(1, 2, 4, 5);
            RowAndColSpan rc3 = new RowAndColSpan(4, 5, 2, 2);
            RowAndColSpan rc4 = new RowAndColSpan(5, 6, 4, 5);
            list.add(rc1);
            list.add(rc2);
            list.add(rc3);
            list.add(rc4);
            pdfTemplateElement.setRowAndColSpanList(list);
            float[] width = {30, 30, 40, 40, 30};
            pdfTemplateElement.setColumnWidth(width);
            DemoBean demoBean = new DemoBean();
            demoBean.setIndex(1);
            demoBean.setName("张三");
            demoBean.setAge(27);
            demoBean.setBirthDay(new Date());
            demoBean.setPhone1("13112345678");
            demoBean.setPhone2("13212345678");
            demoBean.setPhone3("13312345678");
            demoBean.setMoubilPhone1("041112345678");
            demoBean.setMoubilPhone2("041212345678");
            demoBean.setMoubilPhone3("041312345678");
            demoBean.setMoubilPhone4("041412345678");
            demoBean.setMoubilPhone5("041512345678");
            demoBean.setAddress1("辽宁");
            demoBean.setAddress2("大连");
            demoBean.setDoBest1("抽烟");
            demoBean.setDoBest2("喝酒");
            demoBean.setDoBest3("敲代码");
            pdfTemplateElement.setObject(demoBean);
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ItextPdf("d:/blank.pdf", pdfTemplateElement, PdfTemplateType.OBJECT_TABLE).convertAll(out, PdfConvertType.PDF, null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
