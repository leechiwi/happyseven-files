package org.leechiwi.happyseven.files.pdf.itextpdf.template.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.util.Base64;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.RowAndColSpan;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.TableExtendInfo;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.AbstractPdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.*;

@Slf4j
public class TablePdfTemplate extends AbstractPdfTemplate {
    private Map<String, TableExtendInfo> rowAndColSpanMap;
    private Map<String, TableExtendInfo> allRowAndColSpanMap;
    private Map<Integer, Integer> colLeftOfRow;

    public Map<Integer, Integer> getColLeftOfRow() {
        return colLeftOfRow;
    }

    public TablePdfTemplate(InputStream inputStream, PdfTemplateElement pdfTemplateElement) {
        super(inputStream,pdfTemplateElement);
        init();
    }

    private void init() {
        List<RowAndColSpan> rowAndColSpanList = pdfTemplateElement.getRowAndColSpanList();
        rowAndColSpanMap = new HashMap<>();
        colLeftOfRow = new HashMap<>();
        allRowAndColSpanMap = new HashMap<>();
        if(CollectionUtils.isEmpty(rowAndColSpanList)){
            return;
        }
        for (RowAndColSpan rowAndColSpan : rowAndColSpanList) {
            initRow(rowAndColSpan);
        }
    }

    private void initRow(RowAndColSpan rowAndColSpan) {
        for (int i = rowAndColSpan.getRowSpanStart(); i <= rowAndColSpan.getRowSpanEnd(); i++) {
            TableExtendInfo tableExtendInfo = new TableExtendInfo(rowAndColSpan.getRowSpanStart(), rowAndColSpan.getColSpanStart());
            tableExtendInfo.setExtendRow(true);
            boolean firstRow=(i == rowAndColSpan.getRowSpanStart());
            if (firstRow) {
                tableExtendInfo.setRowFirst(true);
                tableExtendInfo.setRowSpan(rowAndColSpan.getRowSpanEnd() - rowAndColSpan.getRowSpanStart() + 1);
            }
            tableExtendInfo.setRowStart(i);
            rowAndColSpanMap.put(i + "", tableExtendInfo);
            if (Objects.isNull(colLeftOfRow.get(i))) {
                colLeftOfRow.put(i, pdfTemplateElement.getColumnWidth().length);
            }
            initColumn(rowAndColSpan, i,firstRow);
        }
    }

    private void initColumn(RowAndColSpan rowAndColSpan, Integer index,boolean firstRow) {
        TableExtendInfo tableExtendInfo = rowAndColSpanMap.get(index.toString());
        for (int j = rowAndColSpan.getColSpanStart(); j <= rowAndColSpan.getColSpanEnd(); j++) {
            TableExtendInfo tableExtendInfoCloned = null;
            try {
                tableExtendInfoCloned = (TableExtendInfo) tableExtendInfo.clone();
            } catch (CloneNotSupportedException e) {

            }
            tableExtendInfoCloned.setColStart(j);
            if (j == rowAndColSpan.getColSpanStart()) {
                tableExtendInfoCloned.setColFirst(true);
                int merged = rowAndColSpan.getColSpanEnd() - rowAndColSpan.getColSpanStart() + 1;
                tableExtendInfoCloned.setColSpan(merged);
                if(firstRow) {
                    colLeftOfRow.put(index, colLeftOfRow.get(index) - merged + 1);
                }
                rowAndColSpanMap.put(tableExtendInfoCloned.getRowStart() + "&" + tableExtendInfoCloned.getColStart(), tableExtendInfoCloned);
            }
            if(!firstRow){
                colLeftOfRow.put(index, colLeftOfRow.get(index)-1);
            }
            allRowAndColSpanMap.put(tableExtendInfoCloned.getRowStart() + "&" + tableExtendInfoCloned.getColStart(), tableExtendInfoCloned);
        }
        rowAndColSpanMap.remove(index.toString());
    }

    @Override
    public boolean doCreate(OutputStream out) {
        Document document = null;
        PdfReader reader = null;
        try {
            reader = new PdfReader(this.inputStream);
            Rectangle pageSize = reader.getPageSize(1);
            document = new Document(pageSize);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            //writer.setPageEvent(new PageEvent());
            // 打开文档
            document.open();
            PdfContentByte cbUnder = writer.getDirectContentUnder();
            PdfImportedPage pageTemplate = writer.getImportedPage(reader, 1);
            cbUnder.addTemplate(pageTemplate, 0, 0);
            //重新开一页面
            //document.newPage();
            createTable(writer, document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (Exception e) {
                log.error("PDF file export close  fail", e);
            }
        }
        return true;
    }

    private void createTable(PdfWriter writer, Document document) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(this.pdfTemplateElement.getColumnWidth());
        table.setTotalWidth(520);
        table.setPaddingTop(500);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);//居中
        table.writeSelectedRows(0, -1, 500, 800, writer.getDirectContentUnder());
        //每页都显示表头,输入几就是第几行的表头固定
        //table.setHeaderRows(2);
        //table.setHeaderRows(3);
        //定义数据的字体
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font textFont = new Font(baseFont, 10, Font.NORMAL);
        List<List<String>> dataList = pdfTemplateElement.getTableDataList();
        int length = pdfTemplateElement.getColumnWidth().length;
        for (int i = 0; i < dataList.size(); i++) {
            int colIndex = 0;
            List<String> columns = dataList.get(i);
            Integer left = colLeftOfRow.get(i + 1);
            for (int j = 0; j < length; j++) {
                if (Objects.nonNull(left) && left == colIndex) {
                    break;
                }
                PdfPCell setCell = createSetCell(columns.get(colIndex), textFont);
                TableExtendInfo tableExtendInfo = rowAndColSpanMap.get((i + 1) + "&" + (j + 1));
                if (Objects.nonNull(left) && left < length || Optional.ofNullable(tableExtendInfo).map(TableExtendInfo::isExtendRow).orElse(false)) {//该行有合并(行和列的都算)
                    TableExtendInfo allTableExtendInfo = allRowAndColSpanMap.get((i + 1) + "&" + (j + 1));
                    if (Objects.isNull(allTableExtendInfo)) {//虽然有合并但是不在合并列的范围内（即行内不参与合并的列）
                        table.addCell(setCell);
                        ++colIndex;
                        continue;
                    }
                    if (Objects.nonNull(tableExtendInfo)) {
                        if (!tableExtendInfo.isRowFirst()) {//如果有合并行单不是合并行范围的起始行则不需要创建单元格，因为合并行范围的起始行已经设置了跨行
                            continue;
                        }
                        if (tableExtendInfo.isColFirst()) {
                            setCell.setColspan(tableExtendInfo.getColSpan());
                        }
                        if (tableExtendInfo.isRowFirst()) {
                            setCell.setRowspan(tableExtendInfo.getRowSpan());
                        }
                    } else {
                        continue;
                    }
                }
                table.addCell(setCell);
                ++colIndex;
            }
        }
        document.add(table);
    }

    //为一个表格添加内容
    private PdfPCell createSetCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        try {
            if(value.startsWith(PdfImageType.FILENAME.getName())){
                cell.setImage(Image.getInstance(value.replace(PdfImageType.FILENAME.getName(),StringUtils.EMPTY)));
            }else if(value.startsWith(PdfImageType.URL.getName())){
                cell.setImage(Image.getInstance(value.replace(PdfImageType.URL.getName(),StringUtils.EMPTY)));
            }else if(value.startsWith(PdfImageType.BASE64.getName())){
                String base64 = value.replace(PdfImageType.BASE64.getName(), StringUtils.EMPTY);
                byte[] bytes = Base64.decodeBase64(base64);
                cell.setImage(Image.getInstance(bytes));
            }else {
                cell.setPhrase(new Phrase(value, font));
            }
        } catch (BadElementException e) {
            cell.setPhrase(new Phrase("bad image", font));
            log.error("cell add image fail",e);
        } catch (IOException e) {
            cell.setPhrase(new Phrase("bad image", font));
            log.error("cell add image fail",e);
        }
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(pdfTemplateElement.getColumnHeight());
        return cell;
    }

}
