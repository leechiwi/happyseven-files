package org.leechiwi.happyseven.files.base.util;

import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Result {
    public static void convertToImageResult(ResultOptions resultOptions, String ext, OutputStream out, List<byte[]> list, OptionResult optionResult){
        if (ResultOptions.ALL_IN_ZIP == resultOptions) {
            Zip.zip(out,list,ext);
            if (out instanceof ByteArrayOutputStream) {
                ArrayList<byte[]> lst = new ArrayList<>();
                lst.add(((ByteArrayOutputStream) out).toByteArray());
                optionResult.setByteList(lst);
            }
        }else if(ResultOptions.MANY == resultOptions){
            optionResult.setByteList(list);
        }
    }
}
