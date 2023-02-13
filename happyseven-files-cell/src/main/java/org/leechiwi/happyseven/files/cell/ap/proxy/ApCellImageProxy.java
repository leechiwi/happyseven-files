package org.leechiwi.happyseven.files.cell.ap.proxy;

import com.aspose.cells.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.base.util.Result;
import org.leechiwi.happyseven.files.cell.Cell;
import org.leechiwi.happyseven.files.cell.ap.ApCell;
import org.leechiwi.happyseven.files.cell.enums.CellConvertType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ApCellImageProxy implements Cell<Workbook> {
    private int dpi;
    private Cell<Workbook> apCell;
    private ResultOptions resultOptions;
    private String range;

    public ApCellImageProxy(int dpi, String range, Object in, ResultOptions resultOptions) {
        if (Objects.nonNull(in)) {
            this.apCell = new ApCell(in);
        } else {
            this.apCell = new ApCell();
        }
        this.dpi = dpi;
        this.resultOptions = resultOptions;
        this.range = range;
    }

    public ApCellImageProxy(int dpi, Object in, ResultOptions resultOptions) {
        this(dpi, null, in, resultOptions);
    }

    @Override
    public boolean convert(InputStream in, OutputStream out, CellConvertType cellConvertType) {
        if (toImage(cellConvertType)) {
            return false;
        }
        return this.apCell.convert(in, out, cellConvertType);
    }

    @Override
    public boolean convert(File file, OutputStream out, CellConvertType cellConvertType) {
        if (toImage(cellConvertType)) {
            return false;
        }
        return this.apCell.convert(file, out, cellConvertType);
    }

    @Override
    public boolean convert(String path, OutputStream out, CellConvertType cellConvertType) {
        if (toImage(cellConvertType)) {
            return false;
        }
        return this.apCell.convert(path, out, cellConvertType);
    }

    @Override
    public boolean convertAll(OutputStream out, CellConvertType cellConvertType, OptionResult optionResult) {
        if (toImage(cellConvertType)) {
            return convertToImg(cellConvertType, out, optionResult);
        }
        return this.apCell.convertAll(out, cellConvertType, optionResult);
    }

    @Override
    public Workbook getWorkbook() {
        return this.apCell.getWorkbook();
    }

    private boolean toImage(CellConvertType cellConvertType) {
        return CellConvertType.getImageTypeSet().contains(cellConvertType);
    }

    private boolean convertToImg(CellConvertType cellConvertType, OutputStream out, OptionResult optionResult) {
        try {
            List<byte[]> list = new ArrayList<>();
            ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
            imgOptions.setCellAutoFit(true);
            imgOptions.setOnePagePerSheet(true);
            String ext = cellConvertType.getExt().substring(1);
            ImageFormat imageFormat = ImageFormat.getImageFormatFromSuffixName(ext);
            imgOptions.setImageFormat(imageFormat);
            WorksheetCollection worksheets = this.apCell.getWorkbook().getWorksheets();
            for (int i = 0, size = worksheets.getCount(); i < size; i++) {
                Worksheet sheet = worksheets.get(i);
                // 设置图片数据的边距
                // A列-L列，1行-39行
                //String area = "A2:C30";
                if (StringUtils.isNotBlank(this.range)) {
                    sheet.getPageSetup().setPrintArea(this.range);
                }
                sheet.getPageSetup().setLeftMargin(1);
                sheet.getPageSetup().setRightMargin(1);
                sheet.getPageSetup().setTopMargin(1);
                sheet.getPageSetup().setBottomMargin(1);
                // 设置字体样式（包含中文）
                // CellsHelper.setFontDir("/");
                // 创建一个纸张底色渲染对象
                SheetRender sheetRender = new SheetRender(sheet, imgOptions);
                for (int j = 0; j < sheetRender.getPageCount(); j++) {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    sheetRender.toImage(j, os);
                    list.add(os.toByteArray());
                }
            }
            Result.convertToImageResult(resultOptions, cellConvertType.getExt(), out, list, optionResult);
        } catch (Exception e) {
            log.error("aspose cell convert image file error", e);
            return false;
        }
        return true;
    }

}
