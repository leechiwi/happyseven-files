import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.cell.ap.ApCell;
import org.leechiwi.happyseven.files.cell.ap.proxy.ApCellImageProxy;
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
            out = new FileOutputStream(new File("d:/test.zip"));
            boolean convert = new ApCellImageProxy(300,"d:/test.xlsx", ResultOptions.ALL_IN_ZIP).convertAll(out, CellConvertType.JPEG,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(in);
        }


    }
}
