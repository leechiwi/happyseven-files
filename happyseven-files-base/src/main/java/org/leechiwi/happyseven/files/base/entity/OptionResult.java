package org.leechiwi.happyseven.files.base.entity;

import java.util.List;

public class OptionResult<T> {
    private List<T> list;

    public OptionResult() {

    }

    public OptionResult(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
