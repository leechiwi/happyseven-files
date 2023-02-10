package org.leechiwi.happyseven.files.ofd.rw.convert;

import lombok.extern.slf4j.Slf4j;;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.ofdrw.reader.OFDReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public abstract class AbstractRwOfdConvert implements RwOfdConvert {
    public abstract void doPre();

    public abstract boolean doConvert(Object in, OutputStream out, OptionResult optionResult);

    protected OFDReader createReader(Object in) {
        OFDReader ofdReader = null;
        try {
            if (in instanceof InputStream) {
                ofdReader = new OFDReader((InputStream) in);
            } else if (in instanceof Path) {
                ofdReader = new OFDReader((Path) in);
            } else if (in instanceof File) {
                ofdReader = new OFDReader(((File) in).toPath());
            } else if (in instanceof String) {
                ofdReader = new OFDReader((String) in);
            } else {
                throw new IllegalArgumentException("不支持的输入格式(input)，仅支持InputStream、Path、File、String");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ofdReader;
    }

    @Override
    public boolean convert(Object in, OutputStream out, OptionResult optionResult) {
        boolean result = false;
        this.doPre();
        result = this.doConvert(in, out,optionResult);
        return result;
    }
    /*protected void zip(OutputStream out, List<InputStream> streamList,String ext) throws HappysevenException{
        int index=0;
        ZipOutputStream zos=null;
        ByteArrayOutputStream os=null;
        if(out instanceof ZipOutputStream){
            zos=(ZipOutputStream)out;
        }else{
            os=new ByteArrayOutputStream();
            zos = new ZipOutputStream(os);
        }
        try {
            for (InputStream inputStream : streamList) {
                byte[] buf = new byte[2 * 1024];
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                zos.putNextEntry(new ZipEntry((index++) + ext));
                // copy文件到zip输出流中
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                inputStream.close();
            }
            if(Objects.nonNull(os)) {
                os.writeTo(out);
            }
        } catch (IOException e) {
            log.error("RwOfd zip error",e);
            throw new HappysevenException("RwOfd zip error");
        }
    }*/
}
