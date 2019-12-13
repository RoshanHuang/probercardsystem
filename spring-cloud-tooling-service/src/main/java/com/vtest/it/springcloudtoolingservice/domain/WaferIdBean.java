package com.vtest.it.springcloudtoolingservice.domain;

import java.io.Serializable;

public class WaferIdBean implements Serializable {
    private String waferNo;

    public String getWaferNo() {
        return waferNo;
    }

    public void setWaferNo(String waferNo) {
        this.waferNo = waferNo;
    }
}
