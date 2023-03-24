package org.leechiwi.happyseven.files.pdf.itextpdf.model;

import lombok.Data;

import java.util.Map;
@Data
public class PdfTemplateElement<T> {
    private Map<String,String> dataMap;
    private T t;
}
