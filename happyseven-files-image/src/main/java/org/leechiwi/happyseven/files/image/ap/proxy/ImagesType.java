package org.leechiwi.happyseven.files.image.ap.proxy;

import org.leechiwi.happyseven.files.doc.enums.WordConvertType;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.util.List;

public enum ImagesType {
    PDF{
        public void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn){
            apImageProxy.createByPdfFile(imagesIn, PdfConvertType.class);
        }
    },
    Word{
        public void imagesBy(ApImageProxy apImageProxy, List<Object> imagesIn){
            apImageProxy.createByWordFile(imagesIn, WordConvertType.class);
        }
    };
    public abstract void imagesBy(ApImageProxy ap, List<Object> imagesIn);
}
