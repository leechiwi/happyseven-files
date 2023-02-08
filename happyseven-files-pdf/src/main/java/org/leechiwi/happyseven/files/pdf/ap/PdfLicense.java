package org.leechiwi.happyseven.files.pdf.ap;

import com.aspose.pdf.License;
import com.aspose.pdf.internal.imaging.FontSettings;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

import java.net.URL;

@Slf4j
public class PdfLicense extends AbstractAspose {
    static {
        loadLicense();
        loadFonts();
    }

    public static void loadLicense() {
        try {
            new License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-word set license error", e);
        }
    }

    public static void loadFonts() {
        FontSettings.setFontsFolder(getFontsDir());
    }
}