package org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation;

import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PdfField {
    int order();
    String prefix() default "";
    String label() default "";
    float fontSize() default 10;
    int fontStype() default 0;
    //1代表上边框 2代表下边框 4代表左边框 8代表右边框
    //需要隐藏那些边框就把对应的值加起来，得到的和就是要设置的值
    int borderSide() default 0;
    int verticalAlign() default 5;
    int horizontalAlign() default 1;
    float columnHeight() default 30;
    String dateFormat() default "yyyy-MM-dd";
    String codeType() default "";
    PdfImageType imageType() default PdfImageType.NONE;//url,filename,byte[],base64,inpustream
    boolean nullLoaded() default false;//针对于属性是引用类型或集合类型值为null是否加载（是否需要生成cell位置）
}
