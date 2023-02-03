package org.leechiwi.happyseven.files.slide.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;
@Slf4j
public class SlideLicense extends AbstractAspose {
    static{
        loadLicense();
    }
    public static void loadLicense(){
        try {
            new com.aspose.slides.License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-slide set license error",e);
        }
    }
}
