import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocHtmlDecorator;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImageDecorator;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImagesDecorator;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocTextDecorator;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApDocTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            ApDocImageDecorator apDocImageDecorator = new ApDocImageDecorator(300, new ApDoc("d:/test6789.doc"), ResultOptions.ALL_IN_ZIP);
            boolean convert = new ApDocHtmlDecorator(apDocImageDecorator).convertAll(out, WordConvertType.PDF,new OptionResult());
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
            List imageList= Stream.of("d:/微信图片_20221031143341.jpg","d:/微信图片_20221031153556.jpg").collect(Collectors.toList());
            ApDocImagesDecorator apDocImagesDecorator=new ApDocImagesDecorator(imageList,new ApDoc(null));
            boolean convert =apDocImagesDecorator.convertAll(out,WordConvertType.PDF,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
    @Test
    public void textConvert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.zip"));
            String in="d:/test.txt";
            ApDocImageDecorator apDocImageDecorator = new ApDocImageDecorator(300, new ApDoc(), ResultOptions.ALL_IN_ZIP);
            ApDocHtmlDecorator apDocHtmlDecorator = new ApDocHtmlDecorator(apDocImageDecorator);
            ApDocTextDecorator apDocTextDecorator =new ApDocTextDecorator(apDocHtmlDecorator,in);
            boolean convert =apDocTextDecorator.convertAll(out,WordConvertType.JPEG,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
}
