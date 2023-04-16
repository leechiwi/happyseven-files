package org.leechiwi.happyseven.files.pdf.itextpdf.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class PdfTemplateElement {
    private float[] columnWidth;
    private float columnHeight=30;
    private Map<String,String> dataMap;//纯文本替换数据，key为制作模板的文本框的名称，value为文本框对应的数值
    private List<List<String>> tableDataList;//1锯齿矩阵，需要配合rowAndColSpanList
    private Object object;//2根据columnWidth长度和t的属性顺序（一个属性的key和value算两列）来计算展示的位置，需要配合rowAndColSpanList
    private List<RowAndColSpan> rowAndColSpanList;
}
