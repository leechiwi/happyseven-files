package org.leechiwi.happyseven.files.doc.ap.factory;

import com.aspose.words.SaveFormat;
import org.leechiwi.happyseven.files.doc.WordConvertTypeFactory;
import org.leechiwi.happyseven.files.doc.enums.WordConvertType;

import java.util.HashMap;
import java.util.Map;

public class ApWordConvertTypeFactory implements WordConvertTypeFactory<Integer> {
   /* private static  Map<String, Integer> wordConvertTypeMapper = new HashMap<>();
    static{
        wordConvertTypeMapper = new HashMap<String, Integer>(){{
            int[] values = SaveFormat.getValues();

        }};
    }*/
    @Override
    public Integer convertWordConvertType(WordConvertType wordConvertType) {
        String type=wordConvertType.toString();
        //String type = wordConvertType.getExt().replace(".","").toUpperCase();
        int intType = SaveFormat.fromName(type);
        return intType;
    }
}
