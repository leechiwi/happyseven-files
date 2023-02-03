import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.slide.ap.ApSlide;
import org.leechiwi.happyseven.files.slide.enums.SlideConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ApSlideTest {
    @Test
    public void convert() {
        //String path = "d:/test.doc";
        //InputStream in = null;
        FileOutputStream out=null;
        try {
            //in = new FileInputStream(new File(path));
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ApSlide("d:/test.pptx").convertAll(out, SlideConvertType.Pdf);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }


    }
}
