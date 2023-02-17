package org.leechiwi.happyseven.files.html.ap;

import com.aspose.html.License;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;
@Slf4j
public class HtmlLicense extends AbstractAspose {
    static{
        loadLicense();
    }
    public static void loadLicense(){
        try {
            new License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-html set license error",e);
        }
    }
}
