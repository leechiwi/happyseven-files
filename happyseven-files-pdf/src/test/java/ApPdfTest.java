import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.ap.ApPdf;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfInnerImagesProxy;
import org.leechiwi.happyseven.files.pdf.ap.proxy.ApPdfProxy;
import org.leechiwi.happyseven.files.pdf.ap.decorators.ApPdfImagesDecorator;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApPdfTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test1.doc"));
            boolean convert = new ApPdfProxy(300,"d:/test.pdf", ResultOptions.NONE).convertAll(out, PdfConvertType.DOC,null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
    @Test
    public void ImagesConvert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            List imageList= Stream.of("d:/微信图片_20221031143341.jpg","d:/微信图片_20230111153127.jpg").collect(Collectors.toList());
            //List imageList= Stream.of("d:/微信图片_20221017161933.jpg","d:/c203d6c5cd19e8b8b392c28ce922916.jpg").collect(Collectors.toList());
            ApPdfImagesDecorator apPdfImagesDecorator=new ApPdfImagesDecorator(imageList,new ApPdf(null));
            boolean convert =apPdfImagesDecorator.convertAll(out,PdfConvertType.PDF,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
    @Test
    public void InnerImagesConvert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.zip"));
            boolean convert = new ApPdfInnerImagesProxy("d:/test.pdf", ResultOptions.ALL_IN_ZIP,true).convertAll(out, PdfConvertType.JPEG,null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
}
