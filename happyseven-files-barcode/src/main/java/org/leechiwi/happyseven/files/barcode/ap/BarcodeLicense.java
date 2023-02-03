package org.leechiwi.happyseven.files.barcode.ap;

import com.aspose.barcode.License;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

@Slf4j
public class BarcodeLicense extends AbstractAspose {
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
