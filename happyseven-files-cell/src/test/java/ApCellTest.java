import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.cell.ap.ApCell;
import org.leechiwi.happyseven.files.cell.enums.CellConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ApCellTest {
    @Test
    public void convert() {
        //String path = "d:/test.doc";
        //InputStream in = null;
        FileOutputStream out=null;
        try {
            //in = new FileInputStream(new File(path));
            out = new FileOutputStream(new File("d:/test.pdf"));
            boolean convert = new ApCell("d:/test.xlsx").convertAll(out, CellConvertType.PDF);
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }


    }
}
