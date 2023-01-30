import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.javacv.JavaCVBarcodeProxy;

public class JavaCVBarcodeTest {
    @Test
    public void getBarcode() {
        JavaCVBarcodeProxy javaCVBarcodeProxy=new JavaCVBarcodeProxy(BarcodeType.NONE);
        String singleBarcodeText = javaCVBarcodeProxy.getSingleBarcodeText("d:/微信图片_20221031153556.jpg");
        System.out.println(singleBarcodeText);
    }
}
