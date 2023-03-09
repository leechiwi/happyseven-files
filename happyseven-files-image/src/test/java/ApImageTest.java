import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.image.ap.ApImage;
import org.leechiwi.happyseven.files.image.ap.proxy.ApImageProxy;
import org.leechiwi.happyseven.files.image.ap.proxy.ImagesType;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ApImageTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            List<Object> images=new ArrayList<>();
            images.add("d:/微信图片_20221031143341.jpg");
            images.add("d:/微信图片_20221031153556.jpg");
            boolean convert = new ApImageProxy<ImageConvertType>(ImageFormat.JPEG,images, ImagesType.IMAGE).convertAll(out, ImageConvertType.PDF,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
}
