package org.leechiwi.happyseven.files.pdf.ap;

import com.aspose.pdf.License;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;
@Slf4j
public class PdfLicense extends AbstractAspose {
    static{
        loadLicense();
    }
    public static void loadLicense(){
        try {
            new License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-word set license error",e);
        }
    }
}
