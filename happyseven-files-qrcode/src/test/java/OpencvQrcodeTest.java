import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.qrcode.config.QrcodeConfigs;
import org.leechiwi.happyseven.files.qrcode.opencv.OpencvQrcode;
import org.leechiwi.happyseven.files.qrcode.opencv.decorators.OpencvPdfQrcodeDecorator;

import java.util.List;

public class OpencvQrcodeTest {
    @Test
    public void getQrcode(){
        QrcodeConfigs qrcodeConfigs = QrcodeConfigs.build("D:/ideaworkspace/happyseven-files/opencv",true);
        String text = new OpencvQrcode(qrcodeConfigs).getSingleQrcodeText("d:/微信图片_20221017161933.jpg");
        System.out.println(text);
    }
    @Test
    public void getPdfQrcode(){
        QrcodeConfigs qrcodeConfigs = QrcodeConfigs.build("D:/ideaworkspace/happyseven-files/opencv",true);
        List<String> qrcodeTextList = new OpencvPdfQrcodeDecorator("d:/test.pdf", new OpencvQrcode(qrcodeConfigs)).getQrcodeTextAsync(null,0);
        if(CollectionUtils.isNotEmpty(qrcodeTextList)){
            for (String s : qrcodeTextList) {
                System.out.println(s);
            }
        }
    }
}
