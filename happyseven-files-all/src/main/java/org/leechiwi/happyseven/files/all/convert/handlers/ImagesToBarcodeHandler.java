package org.leechiwi.happyseven.files.all.convert.handlers;

import org.leechiwi.happyseven.files.all.convert.ConvertChainHandler;
import org.leechiwi.happyseven.files.barcode.ap.ApBarcode;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;

import java.io.OutputStream;
import java.util.List;

public class ImagesToBarcodeHandler extends ConvertChainHandler {
    @Override
    public boolean doHandler(Object in, OutputStream out, Object fileConvertType, ResultOptions resultOptions, OptionResult optionResult) {
        List list=(List)in;
        ApBarcode apBarcode = new ApBarcode(BarcodeType.CODE_128);
        List<String> codeTextList=apBarcode.getBarcodeTextAsync(null,list,2);
        optionResult.setList(codeTextList);
        return true;
    }

    @Override
    public Object doHandlerPost(Object object) {
        return null;
    }
}
