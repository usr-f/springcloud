package com.qif.cloud.mybatis.old.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/26 0026.
 */
public class Girl_spec implements Serializable {

    private int specId;
    private String specName;

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
