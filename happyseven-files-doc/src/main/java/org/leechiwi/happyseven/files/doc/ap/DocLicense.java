package org.leechiwi.happyseven.files.doc.ap;

import com.aspose.words.License;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

import java.io.InputStream;
@Slf4j
public class DocLicense extends AbstractAspose {
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
