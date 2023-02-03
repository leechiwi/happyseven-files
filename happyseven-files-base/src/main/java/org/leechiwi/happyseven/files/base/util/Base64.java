package org.leechiwi.happyseven.files.base.util;

public class Base64 {
        public static String encodeBase64(byte[] bytes) {
            return java.util.Base64.getEncoder().encodeToString(bytes);
        }

        public static byte[] decodeBase64(String s) {
            return java.util.Base64.getDecoder().decode(s);
        }
}
