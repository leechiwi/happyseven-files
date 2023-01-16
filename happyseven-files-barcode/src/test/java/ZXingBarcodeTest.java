import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.zxing.ZXingBarcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ZXingBarcodeTest {
    @Test
    public void createBarcode() {
        ZXingBarcode ZXingBarcode = new ZXingBarcode(BarcodeType.CODE_128);
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/barcode.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZXingBarcode.CreateBarcode("1017227329895", out);
    }

    @Test
    public void getBarcode() {
        ZXingBarcode ZXingBarcode = new ZXingBarcode(BarcodeType.CODE_128);
        String codeText = ZXingBarcode.getSingleBarcodeText("d:/微信图片_20221031143341.jpg");
        System.out.println(codeText);
    }
}
