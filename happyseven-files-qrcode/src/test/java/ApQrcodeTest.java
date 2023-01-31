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
            out = new FileOutputStream(new File("d:/qrcode.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new ApQrcode().createQrcode("createQrcode",out);
    }
    @Test
    public void getQrcode(){//目前测试该方法识别不准确
        String text = new ApQrcode().getSingleQrcodeText("d:/qrcode.png");
        System.out.println(text);
    }
}
