package org.leechiwi.happyseven.files.base.util;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
public class Zip {
    public static void zip(OutputStream out, List<byte[]> streamList, String ext) throws HappysevenException {
        ZipOutputStream zipOut = new ZipOutputStream(out);
        int index=0;
        try {
            for (byte[] bytes : streamList) {
                // 创建一个ZipEntry
                zipOut.putNextEntry(new ZipEntry((index++) + ext));
                // 将源文件的字节内容，写入zip压缩包
                zipOut.write(bytes);
                // 结束当前zipEntry
                zipOut.closeEntry();
            }
            zipOut.finish();
            zipOut.close();
        } catch (IOException e) {
            log.error("zip error",e);
            throw new HappysevenException("zip error");
        }
    }
}
