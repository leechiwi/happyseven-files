import com.aspose.barcode.barcoderecognition.DecodeType;
import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.ap.ApBarcode;

public class ApBarCodeTest {
    @Test
    public void  getBarcodeText(){
        ApBarcode apBarcode = new ApBarcode();
        String codeText=apBarcode.getSingleBarcodeText("d:/新建文件夹123456/111.png", DecodeType.CODE_128);
        System.out.println(codeText);
    }
}
