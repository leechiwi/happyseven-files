import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.ap.ApBarcode;
import org.leechiwi.happyseven.files.barcode.ap.decorators.ApPdfBarcodeDecorator;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ApBarCodeTest {
    @Test
    public void  getBarcodeText(){
        ApBarcode apBarcode = new ApBarcode(BarcodeType.CODE_128);
        String codeText=apBarcode.getSingleBarcodeText("d:/新建文件夹123456/111.png");
        System.out.println(codeText);
    }
    @Test
    public void createBarcode(){
        ApBarcode apBarcode = new ApBarcode(BarcodeType.CODE_128);
        OutputStream out=null;
        try {
            out=new FileOutputStream(new File("d:/barcode.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        apBarcode.CreateBarcode("1017227329895", out);
    }
    @Test
    public void getPdfBarcode(){
        List<String> barcodeTextList = new ApPdfBarcodeDecorator("d:/test.pdf", new ApBarcode(BarcodeType.CODE_128)).getBarcodeTextAsync(null, null, 2);
        if(CollectionUtils.isNotEmpty(barcodeTextList)){
            for (String s : barcodeTextList) {
                System.out.println(s);
            }
        }
    }
}
