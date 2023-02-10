import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
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
            out = new FileOutputStream(new File("d:/test.zip"));
            boolean convert = new RwOfd(ResultOptions.ALL_IN_ZIP).convertAll("d:/test.ofd", out, OfdConvertType.PNG,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }
    }
}
