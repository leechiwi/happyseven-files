import org.junit.Test;
import org.leechiwi.happyseven.files.qrcode.zxing.ZXingQrcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ZXingQrcodeTest {
    @Test
    public void createQrcode(){
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/qrcode.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new ZXingQrcode().createQrcode("ZXingQrcode",out);
    }
    @Test
    public void getQrcode(){
        String path="d:/qrcode.jpg";
        String text = new ZXingQrcode().getSingleQrcodeText(path);
        System.out.println(text);
    }
}
