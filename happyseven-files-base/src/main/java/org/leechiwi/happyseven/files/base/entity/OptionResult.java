package org.leechiwi.happyseven.files.base.entity;

import java.util.List;

public class OptionResult {
    private List<byte[]> byteList;

    public OptionResult() {

    }
    public OptionResult(List<byte[]> byteList) {
        this.byteList = byteList;
    }

    public List<byte[]> getByteList() {
        return byteList;
    }

    public void setByteList(List<byte[]> byteList) {
        this.byteList = byteList;
    }
}
