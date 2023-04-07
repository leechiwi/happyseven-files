package org.leechiwi.happyseven.files.pdf.itextpdf.model;

import lombok.Data;

@Data
public class TableExtendInfo implements Cloneable{
    private int rowStart;//行号，和colStar共同决定第几行第几列开始纵向扩展
    private int rowSpan;
    private int colStart;//列号，和rowStart共同决定第几行第几列开始横向扩展
    private int colSpan;
    private boolean isRowFirst;//行号是否是开始扩展的行号主要指rowStart
    private boolean isColFirst;//列号是否是开始扩展的行号主要指colStart
    private boolean isExtendRow;//是否是扩展的行范围内的行
    private int colLeft;//每行合并后剩下的单实际单元格数

    public TableExtendInfo(Integer rowStart, Integer colStart) {
        this.rowStart = rowStart;
        this.colStart = colStart;
        this.rowSpan=1;
        this.colSpan=1;
        isRowFirst=false;
        isColFirst=false;
    }
    public Object clone() throws CloneNotSupportedException{
            return super.clone();
    }
}
