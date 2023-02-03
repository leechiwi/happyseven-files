package org.leechiwi.happyseven.files.image.ap;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

@Slf4j
public class ImageLicense extends AbstractAspose {
    static{
        loadLicense();
    }
    public static void loadLicense(){
        try {
            new com.aspose.imaging.License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-word set license error",e);
        }
    }
}
