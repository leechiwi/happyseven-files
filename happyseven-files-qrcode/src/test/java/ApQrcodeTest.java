import org.junit.Test;
import org.leechiwi.happyseven.files.qrcode.ap.ApQrcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ApQrcodeTest {
    @Test
    public void createQrcode(){
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("d:/qrcode.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new ApQrcode().createQrcode("ApQrcode",out);
    }
    @Test
    public void getQrcode(){//目前测试该方法不是所有都能识别，等同于zxing的识别
        String text = new ApQrcode().getSingleQrcodeText("d:/qrcode.jpg");
        System.out.println(text);
    }
}
