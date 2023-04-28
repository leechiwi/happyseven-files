import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import lombok.Builder;
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
import org.leechiwi.happyseven.files.pdf.itextpdf.handler.CellHandler;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.RowAndColSpan;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation.PdfField;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfTemplateType;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.proxy.TablePdfTemplateProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApPdfTest {
    @Data
    private static class DemoBean {
        @PdfField(order = 0,fontSize = 15,fontStype = Font.BOLD,borderSide = 15,columnHeight = 90)
        private String title;
        @PdfField(order = 1)
        private Integer index;
        @PdfField(order = 3,prefix = "age")
        private Integer age;
        @PdfField(order = 2)
        private String name;
        @PdfField(order = 4)
        private Date birthDay;
        @PdfField(order = 5, label = "电话1",columnHeight = 50)
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
        @PdfField(order = 18)
        private String image1;
        @PdfField(order = 19)
        private String image2;
        @PdfField(order = 20,imageType = PdfImageType.FILENAME)
        private String image;
        @PdfField(order = 21)
        private String image3;
        @PdfField(order = 22)
        private String image4;
        @PdfField(order = 21)
        private String image5;
        @PdfField(order = 22)
        private String image6;
        @PdfField(order = 23)
        private String image7;
        @PdfField(order = 24)
        private String image8;
        @PdfField(order = 27)
        private String blank;
        @PdfField(order = 28)
        private DemoBean2 demoBean2;
        @PdfField(order = 25,nullLoaded = true)
        private String indexall;
        @PdfField(order = 26,nullLoaded = true)
        private List<DemoBean2> demoBeanList;

    }
    @Data
    @Builder
    private static class DemoBean2 {
        @PdfField(order = 1,nullLoaded = true)
        private String name;
        @PdfField(order = 3,nullLoaded = true)
        private Integer age;
        @PdfField(order = 4,nullLoaded = true)
        private Date birthday;
        @PdfField(order = 5,nullLoaded = true)
        private String phone;
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
            /*PdfTemplateElement pdfTemplateElement=new PdfTemplateElement();
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
            RowAndColSpan rc6 = new RowAndColSpan(1, 1, 1, 5);
            RowAndColSpan rc1 = new RowAndColSpan(2, 3, 2, 3);
            RowAndColSpan rc2 = new RowAndColSpan(2, 3, 4, 5);
            RowAndColSpan rc3 = new RowAndColSpan(5, 6, 2, 2);
            RowAndColSpan rc4 = new RowAndColSpan(6, 7, 4, 5);
            RowAndColSpan rc5 = new RowAndColSpan(8, 11, 3, 5);
            list.add(rc1);
            list.add(rc2);
            list.add(rc3);
            list.add(rc4);
            list.add(rc5);
            list.add(rc6);
            //pdfTemplateElement.setRowAndColSpanList(list);
            float[] width = {30, 30, 40, 40, 30};
            pdfTemplateElement.setColumnWidth(width);
            DemoBean demoBean = new DemoBean();
            demoBean.setTitle("个人介绍");
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
            demoBean.setImage1("image1");
            demoBean.setImage2("image2");
            demoBean.setImage("d:/qrcode.png");
            demoBean.setImage3("image3");
            demoBean.setImage4("image4");
            demoBean.setImage5("image5");
            demoBean.setImage6("image6");
            demoBean.setImage7("image7");
            demoBean.setImage8("image8");
            demoBean.setBlank("blank");
            DemoBean2 demoBean2 = DemoBean2.builder()
                    .age(23)
                    .birthday(new Date())
                    .name("happyseven")
                    .phone("18688888888").build();
            demoBean.setDemoBean2(demoBean2);
            demoBean.setIndexall("110");
            DemoBean2 demoBeanLst1 = DemoBean2.builder()
                    .age(24)
                    .birthday(new Date())
                    .name("happyseven1")
                    .phone("18688888888").build();
            DemoBean2 demoBeanLst2 = DemoBean2.builder()
                    .age(25)
                    .birthday(new Date())
                    .name("happyseven2")
                    .phone("18688888889").build();
            demoBean.setDemoBeanList(Stream.of(demoBeanLst1,demoBeanLst2).collect(Collectors.toList()));
            RowAndColSpan rc7 = new RowAndColSpan(12, 12+demoBean.getDemoBeanList().size()-1, 1, 1);
            list.add(rc7);
            pdfTemplateElement.setRowAndColSpanList(list);
            pdfTemplateElement.setObject(demoBean);
            CellHandler cellHandler= (cellElement, cell)->{
                if(cellElement.getPrefix().equals("age")){
                    String content = cell.getPhrase().getContent();
                    if(Integer.valueOf(content)>18){
                        cell.setBackgroundColor(BaseColor.RED);
                    }
                }
            };
            pdfTemplateElement.setCellHandler(cellHandler);
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
