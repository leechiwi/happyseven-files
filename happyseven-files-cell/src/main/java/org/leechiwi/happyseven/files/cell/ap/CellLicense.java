package org.leechiwi.happyseven.files.cell.ap;


import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

@Slf4j
public class CellLicense extends AbstractAspose {
    static{
        loadLicense();
    }
    public static void loadLicense(){
        try {
            new com.aspose.cells.License().setLicense(getLicense());
        } catch (Exception e) {
            log.error("aspose-cell set license error",e);
        }
    }
}
