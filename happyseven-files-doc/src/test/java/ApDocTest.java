import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.*;

public class ApDocTest {
    @Test
    public void convert() {
        //String path = "d:/test.doc";
        //InputStream in = null;
        FileOutputStream out=null;
        try {
            //in = new FileInputStream(new File(path));
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ApDoc("d:/test.doc").convertAll(out, WordConvertType.PDF);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }


    }
}
