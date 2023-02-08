package org.leechiwi.happyseven.files.base.read;

import org.leechiwi.happyseven.files.base.exception.HappysevenException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileRead {
    public InputStream loadFile(Object document) throws HappysevenException{
        InputStream inputStream = null;
        try {
            inputStream = null;
            if (document instanceof InputStream) {
                inputStream = (InputStream) document;
            } else if (document instanceof File) {
                inputStream = new FileInputStream((File) document);
            } else if (document instanceof String) {
                inputStream = new FileInputStream(new File((String) document));
            }else{
                throw new HappysevenException("unsupport filetype");
            }
        } catch (Exception e) {
            throw new HappysevenException("load file type error");
        }
        return inputStream;
    }
}
