import org.junit.Test;
import org.leechiwi.happyseven.files.qrcode.config.QrcodeConfigs;
import org.leechiwi.happyseven.files.qrcode.javacv.JavacvQrcode;

public class JavacvQrcodeTest {
    @Test
    public void getQrcode(){
        QrcodeConfigs qrcodeConfigs = QrcodeConfigs.build("D:/ideaworkspace/happyseven-files/opencv",false);
        String text = new JavacvQrcode(qrcodeConfigs).getSingleQrcodeText("d:/c203d6c5cd19e8b8b392c28ce922916.jpg");
        System.out.println(text);
    }
}
