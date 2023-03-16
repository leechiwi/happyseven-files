package org.leechiwi.happyseven.files.qrcode;

import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface Qrcode {
    List<String> getQrcodeText(Object image);

    String getSingleQrcodeText(Object image);

    boolean createQrcode(String text, OutputStream out);

    default List<String> getQrcodeTextAsync(ThreadPoolExecutor pool, List<Object> imageList, int thread) {
        return null;
    }
}
