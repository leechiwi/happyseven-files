package org.leechiwi.happyseven.files.pdf.itextpdf.template.enums;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public enum PdfImageType {
    NONE("none:"){
        public  String imageToString(Object o){
            return StringUtils.EMPTY;
        }
    },
    URL("url:"){
        public  String imageToString(Object o){
            return this.name()+ Objects.toString(o, StringUtils.EMPTY);
        }
    },
    FILENAME("filename"){
        public  String imageToString(Object o){
            return this.name()+(String)o;
        }
    },
    BYTE("base64:"){
        public  String imageToString(Object o){
            return this.name()+ Base64.encodeBase64((byte[])o);
        }
    },
    BASE64("base64:"){
        public  String imageToString(Object o){
            return this.name()+ Objects.toString(o, StringUtils.EMPTY);
        }
    },
    STREAM("base64:"){
        public  String imageToString(Object o){
            return this.name()+Base64.inputStreamToBase64((InputStream)o);
        }
    };
    private String name;

    private PdfImageType(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    private static final Set<PdfImageType> usedTypeSet = EnumSet.of(URL, FILENAME, BYTE, BASE64,STREAM);

    public static Set<PdfImageType> getUsedTypeSet(){
        return usedTypeSet;
    }
    public abstract String imageToString(Object o);
}
