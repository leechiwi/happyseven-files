import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.image.ap.ApImage;
import org.leechiwi.happyseven.files.image.enums.ImageConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ApImageTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            //in = new FileInputStream(new File(path));
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ApImage(ImageFormat.JPEG,"d:/微信图片_20221031143341.jpg").convertAll(out, ImageConvertType.PDF);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }
    }
}
