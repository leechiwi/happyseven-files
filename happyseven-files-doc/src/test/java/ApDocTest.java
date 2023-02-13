import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.doc.ap.ApDoc;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocHtmlDecorator;
import org.leechiwi.happyseven.files.doc.ap.decorators.ApDocImageDecorator;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ApDocTest {
    @Test
    public void convert() {
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File("d:/test.pdf"));
            ApDocImageDecorator apDocImageDecorator = new ApDocImageDecorator(300, new ApDoc("d:/test.doc"), ResultOptions.ALL_IN_ZIP);
            boolean convert = new ApDocHtmlDecorator(apDocImageDecorator).convertAll(out, WordConvertType.PDF,new OptionResult());
            System.out.println("result=" + convert);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(out);
        }


    }
}
