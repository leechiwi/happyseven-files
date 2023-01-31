package org.leechiwi.happyseven.files.qrcode;

import java.io.OutputStream;
import java.util.List;

public interface Qrcode {
    List<String> getQrcodeText(Object image);

    String getSingleQrcodeText(Object image);

    boolean createQrcode(String text, OutputStream out);
}
