import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.all.convert.impl.SlideToAnyByPdf;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SlideToAnyByPdfTest {
    @Test
    public void convert() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new java.io.File("d:/test.zip"));
            SlideToAnyByPdf slideToAnyByPdf = new SlideToAnyByPdf("d:/test.pptx", ResultOptions.ALL_IN_ZIP);
            boolean result = slideToAnyByPdf.convertAll(out, PdfConvertType.JPEG, new OptionResult());
            System.out.println("result=" + result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }


}
