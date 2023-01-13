import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import com.aspose.barcode.generation.BarcodeClassifications;
import com.aspose.barcode.generation.BaseEncodeType;
import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.ap.ApBarcode;
import org.leechiwi.happyseven.files.barcode.ap.encodetype.CustomEncodeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ApBarCodeTest {
    @Test
    public void  getBarcodeText(){
        ApBarcode apBarcode = new ApBarcode();
        String codeText=apBarcode.getSingleBarcodeText("d:/新建文件夹123456/111.png", DecodeType.CODE_128);
        System.out.println(codeText);
    }
    @Test
    public void createBarcode(){
        ApBarcode apBarcode = new ApBarcode();
        OutputStream out=null;
        try {
            out=new FileOutputStream(new File("d:/barcode.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SingleDecodeType code128 = DecodeType.CODE_128;
        BaseEncodeType baseEncodeType=new CustomEncodeType(code128.getTypeIndex(),code128.getTypeName(),BarcodeClassifications.NONE);
        apBarcode.CreateBarcode("1017227329895", baseEncodeType, out);
    }
}
