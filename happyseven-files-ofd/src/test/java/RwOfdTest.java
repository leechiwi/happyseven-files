import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.ofd.enums.OfdConvertType;
import org.leechiwi.happyseven.files.ofd.rw.RwOfd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class RwOfdTest {
    @Test
    public void convert() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new RwOfd().convertAll("d:/test.ofd", out, OfdConvertType.PDF);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }
    }
}
