package org.leechiwi.happyseven.files.base.ap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.base.util.Decrypt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
@Slf4j
public abstract class AbstractAspose {
    protected static InputStream getLicense() {
        return new ByteArrayInputStream(AbstractAspose.LicenseLoader.bytes);
    }
    private static class LicenseLoader{
        private static final String SECRET_KEY = "c2xAMzl5XipVITVnOEViZQ==";
        private static final String IV_KEY = "0000000000000000";
        private static byte[] bytes;
        static {
            try {
                InputStream resourceStream = AbstractAspose.class.getResourceAsStream("/aspose/license.txt");
                String text = IOUtils.toString(resourceStream, "UTF-8").trim();
                text = Decrypt.decrypt(SECRET_KEY,IV_KEY,text);
                bytes = text.getBytes(StandardCharsets.UTF_8);
                log.debug("init aspose License successful.");
            } catch (Exception e) {
                throw new HappysevenException("init License info error!", e);
            }
        }
    }
}
