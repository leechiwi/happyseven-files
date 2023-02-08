import org.junit.Test;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.barcode.javacv.JavaCVBarcode;

public class JavaCVBarcodeTest {
    @Test
    public void getBarcode() {
        JavaCVBarcode javaCVBarcode=new JavaCVBarcode(BarcodeType.NONE);
        String singleBarcodeText = javaCVBarcode.getSingleBarcodeText("d:/微信图片_20221031153556.jpg");
        System.out.println(singleBarcodeText);
    }
}
