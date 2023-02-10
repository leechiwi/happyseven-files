import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.text.enums.TextConvertType;
import org.leechiwi.happyseven.files.text.itext.ItextText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ItextTextTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ItextText("d:/test.txt").convertAll(out, TextConvertType.PDF,null);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }
}
