package org.leechiwi.happyseven.files.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class Base64 {
        public static String encodeBase64(byte[] bytes) {
            return java.util.Base64.getEncoder().encodeToString(bytes);
        }

        public static byte[] decodeBase64(String s) {
            return java.util.Base64.getDecoder().decode(s);
        }

        public static String inputStreamToBase64(InputStream inputStream){
            String result= StringUtils.EMPTY;
            try {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                result=encodeBase64(bytes);
            } catch (IOException e) {
                log.error("inputStreamToBase64 error");
            }
            return result;
        }
}
