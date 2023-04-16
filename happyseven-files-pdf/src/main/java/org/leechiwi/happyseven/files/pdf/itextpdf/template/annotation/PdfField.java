package org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation;

import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PdfField {
    int order();
    String label() default "";
    String dateFormat() default "yyyy-MM-dd";
    String codeType() default "";
    PdfImageType imageType() default PdfImageType.NONE;//url,filename,byte[],base64,inpustream
    boolean isCollection() default false;
    Class genericClass() default Object.class;
}
