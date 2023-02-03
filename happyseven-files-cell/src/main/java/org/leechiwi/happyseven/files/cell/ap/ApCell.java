package org.leechiwi.happyseven.files.cell.ap;

import com.aspose.cells.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.cell.Cell;
import org.leechiwi.happyseven.files.cell.enums.CellConvertType;

import java.io.*;

@Slf4j
public class ApCell extends CellLicense implements Cell {
    private Workbook workbook;
    public ApCell(){

    }
    public ApCell(Object workbook) {
        this.workbook=createWorkbook(workbook);
    }
    private Workbook createWorkbook(Object workbook) {
        Workbook wb=null;
        try {
            InputStream inputStream=null;
            if(workbook instanceof InputStream){
                inputStream=(InputStream) workbook;
            }else if(workbook instanceof File){
                inputStream=new FileInputStream((File)workbook);
            }else if(workbook instanceof String){
                inputStream=new FileInputStream(new File((String)workbook));
            }
            wb=new Workbook(inputStream);
        } catch (Exception e) {
            log.error("create aspose word Document error", e);
            //throw new HappysevenException("create aspose word Document error", e);
        }
        return wb;
    }
    @Override
    public boolean convert(InputStream in, OutputStream out, CellConvertType cellConvertType) {
        try {
            Workbook workbook = new Workbook(in);
            workbook.save(out, cellConvertType.getCode());
        } catch (Exception e) {
            log.error("aspose cell convert stream error",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(File file, OutputStream out, CellConvertType cellConvertType) {
        try {
            convert(new FileInputStream(file),out,cellConvertType);
        } catch (FileNotFoundException e) {
            log.error("aspose cell convert file error",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convert(String path, OutputStream out, CellConvertType cellConvertType) {
        try {
            convert(new FileInputStream(new File(path)),out,cellConvertType);
        } catch (FileNotFoundException e) {
            log.error("aspose cell convert path error",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean convertAll(OutputStream out, CellConvertType cellConvertType) {
        try {
            this.workbook.save(out,cellConvertType.getCode());
        } catch (Exception e) {
            log.error("aspose cell convert all error",e);
            return false;
        }
        return true;
    }
}
