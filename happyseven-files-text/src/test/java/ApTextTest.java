import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.text.ap.ApText;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ApTextTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ApText("d:/test.txt").convertAll(out, TextConvertType.PDF);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
}
