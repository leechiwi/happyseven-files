import org.junit.Test;
import org.leechiwi.happyseven.files.qrcode.config.QrcodeConfigs;
import org.leechiwi.happyseven.files.qrcode.opencv.OpencvQrcode;

public class OpencvQrcodeTest {
    @Test
    public void getQrcode(){
        QrcodeConfigs qrcodeConfigs = QrcodeConfigs.build("D:/ideaworkspace/happyseven-files/opencv",true);
        String text = new OpencvQrcode(qrcodeConfigs).getSingleQrcodeText("d:/微信图片_20221017161933.jpg");
        System.out.println(text);
    }
}
